package cord.eoeo.momentwo.config.oauth;

import cord.eoeo.momentwo.user.application.port.out.oauth.GetOAuthUserInfoPort;
import cord.eoeo.momentwo.user.application.port.out.oauth.OAuth2UserInfo;
import cord.eoeo.momentwo.user.application.port.out.oauth.OAuth2UserInfoFactoryPort;
import cord.eoeo.momentwo.user.domain.OAuthInfo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final OAuth2UserInfoFactoryPort oAuth2UserInfoFactoryPort;
    private final GetOAuthUserInfoPort getOAuthUserInfoPort;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // OAuth2 로그인 시 pk 값
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
                .getUserNameAttributeName();

        // 서비스 구분 코드 ex) Google, Kakao
        String code = userRequest.getClientRegistration().getRegistrationId();

        // 소셜 메소드 확인 이후 값들은 Map 에 형태로 반환
        OAuthInfo oAuthInfo = OAuthInfo.from(code);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // 소셜 종류에 상관없이 식별자를 받아오기
        OAuth2UserInfo oAuth2UserInfo = oAuth2UserInfoFactoryPort.getOAuth2UserInfo(oAuthInfo, attributes);
        String userIdentifier = oAuth2UserInfo.getOauth2Identifier();

        // 유저 확인 (있으면 정보 반환, 없으면 생성)
        User user = compareOAuthInfoName(userIdentifier, oAuthInfo, oAuth2UserInfo);

        // 커스텀 Security Context 에 객체 생성
        return new UserPrincipal(user, attributes, userNameAttributeName);
    }

    // 어떤 소셜 로그인인지 확인하여 User 정보 리턴
    private User compareOAuthInfoName(String userIdentifier, OAuthInfo oAuthInfo, OAuth2UserInfo oAuth2UserInfo) {
        if(OAuthInfo.KAKAO.equals(oAuthInfo)) {
            return getOAuthUserInfoPort
                    .userFindByOAuthInfo(oAuthInfo, oAuth2UserInfo.getAccount(), oAuth2UserInfo.getProfiles());
        }
        return getOAuthUserInfoPort.userFindByOAuthInfo(userIdentifier, oAuthInfo, oAuth2UserInfo.attributes);
    }
}
