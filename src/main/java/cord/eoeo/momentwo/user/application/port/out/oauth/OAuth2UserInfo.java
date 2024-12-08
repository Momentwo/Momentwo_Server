package cord.eoeo.momentwo.user.application.port.out.oauth;

import java.util.Map;

public abstract class OAuth2UserInfo {
    public Map<String, Object> attributes;

    public abstract String getOauthName();
    public abstract String getOauth2Code();
    public abstract String getOauth2Identifier();
    public abstract Map<String, Object> getAccount();
    public abstract Map<String, Object> getProfiles();
}
