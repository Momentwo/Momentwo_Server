package cord.eoeo.momentwo.user.application.port.out.find;

import cord.eoeo.momentwo.user.domain.OAuthInfo;
import cord.eoeo.momentwo.user.domain.User;

import java.util.Optional;

public interface UserFindByOAuthInfoRepo {
    Optional<User> userFindByOAuthInfo(String username, OAuthInfo oAuthInfo);
}
