package cord.eoeo.momentwo.user.adapter.out.oauth;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cord.eoeo.momentwo.user.application.port.out.oauth.OAuth2UserInfo;
import cord.eoeo.momentwo.user.domain.OAuthInfo;

import java.util.Map;

public class KakaoOAuth2UserInfoAdapter extends OAuth2UserInfo {
    private final Map<String, Object> kakaoAccount;
    private final Map<String, Object> kakaoProfiles;

    public KakaoOAuth2UserInfoAdapter(Map<String, Object> attributes) {
        this.attributes = attributes;
        ObjectMapper objectMapper = new ObjectMapper();
        kakaoAccount = objectMapper
                .convertValue(attributes.get(OAuthInfo.KAKAO.getKey()), new TypeReference<Map<String, Object>>() {});
        kakaoProfiles = objectMapper
                .convertValue(kakaoAccount.get("profile"), new TypeReference<Map<String, Object>>() {});
    }

    @Override
    public String getOauthName() {
        return (String) attributes.get(OAuthInfo.KAKAO.getName());
    }

    @Override
    public String getOauth2Code() {
        return (String) attributes.get(OAuthInfo.KAKAO.getCode());
    }

    @Override
    public String getOauth2Identifier() {
        return (String) kakaoAccount.get(OAuthInfo.KAKAO.getIdentifier());
    }

    @Override
    public Map<String, Object> getAccount() {
        return kakaoAccount;
    }

    @Override
    public Map<String, Object> getProfiles() {
        return kakaoProfiles;
    }
}
