package cord.eoeo.momentwo.user.application.port.out.oauth;

import cord.eoeo.momentwo.user.domain.OAuthInfo;

import java.util.Map;

public interface OAuth2UserInfoFactoryPort {
    OAuth2UserInfo getOAuth2UserInfo(OAuthInfo oAuthInfo, Map<String, Object> attributes);
}
