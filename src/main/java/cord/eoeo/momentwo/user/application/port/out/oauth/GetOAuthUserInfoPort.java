package cord.eoeo.momentwo.user.application.port.out.oauth;

import cord.eoeo.momentwo.user.domain.OAuthInfo;
import cord.eoeo.momentwo.user.domain.User;

import java.util.Map;

public interface GetOAuthUserInfoPort {
    User userFindByOAuthInfo(String username, OAuthInfo oAuthInfo, Map<String, Object> attributes);
    User userFindByOAuthInfo(OAuthInfo oAuthInfo, Map<String, Object> attributes, Map<String, Object> profiles);
}
