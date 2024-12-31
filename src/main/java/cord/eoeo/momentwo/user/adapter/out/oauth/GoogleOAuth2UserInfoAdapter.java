package cord.eoeo.momentwo.user.adapter.out.oauth;

import cord.eoeo.momentwo.user.application.port.out.oauth.OAuth2UserInfo;
import cord.eoeo.momentwo.user.domain.OAuthInfo;

import java.util.Map;

public class GoogleOAuth2UserInfoAdapter extends OAuth2UserInfo {
    public GoogleOAuth2UserInfoAdapter(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getOauthName() {
        return (String) attributes.get(OAuthInfo.GOOGLE.getName());
    }

    @Override
    public String getOauth2Code() {
        return (String) attributes.get(OAuthInfo.GOOGLE.getCode());
    }

    @Override
    public String getOauth2Identifier() {
        return (String) attributes.get(OAuthInfo.GOOGLE.getIdentifier());
    }

    @Override
    public Map<String, Object> getAccount() {
        return null;
    }

    @Override
    public Map<String, Object> getProfiles() {
        return null;
    }
}
