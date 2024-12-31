package cord.eoeo.momentwo.config.oauth;

import cord.eoeo.momentwo.user.domain.OAuthInfo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

@Component
public class CustomAuthorizationCodeTokenResponseClient
        implements OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> {

    @Override
    public OAuth2AccessTokenResponse getTokenResponse(OAuth2AuthorizationCodeGrantRequest authorizationGrantRequest) {
        ClientRegistration registration = authorizationGrantRequest.getClientRegistration();

        // 카카오 Oauth2 요청이 아닌 경우 (바디에 값을 담아야 함)
        if(!OAuthInfo.KAKAO.name().equals(registration.getClientName().toUpperCase())) {
            return new DefaultAuthorizationCodeTokenResponseClient().getTokenResponse(authorizationGrantRequest);
        }

        // POST 요청 BODY 구현
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("code", authorizationGrantRequest.getAuthorizationExchange().getAuthorizationResponse().getCode());
        body.add("redirect_uri", authorizationGrantRequest.getAuthorizationExchange().getAuthorizationResponse().getRedirectUri());
        body.add("client_id", registration.getClientId());
        body.add("client_secret", registration.getClientSecret());

        // RestTemplate 를 통한 POST 요청
        RestTemplate restTemplate = new RestTemplate();

        // Header 설정 (공식문서 참조)
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // Http 객체 생성 (Body + Header)
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        // (kakao) TokenUrl 로 POST 요청
        ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(
                registration.getProviderDetails().getTokenUri(),
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<Map<String, Object>>() {} // Map 타입 설정
        );

        // OAuth2AccessTokenResponse 와 매핑되지 않아 응답값을 직접 Map 으로 적용
        Map<String, Object> responseData = responseEntity.getBody();

        if(responseEntity.getBody() == null) {
            throw new IllegalArgumentException("토큰이 비어있습니다.");
        }

        return OAuth2AccessTokenResponse
                .withToken((String) Objects.requireNonNull(responseData).get("access_token"))
                .refreshToken((String) responseData.get("refresh_token"))
                .tokenType(OAuth2AccessToken.TokenType.BEARER)
                .additionalParameters(responseData)
                .build();
    }
}
