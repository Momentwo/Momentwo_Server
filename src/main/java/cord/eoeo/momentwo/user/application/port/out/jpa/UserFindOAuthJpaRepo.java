package cord.eoeo.momentwo.user.application.port.out.jpa;

import cord.eoeo.momentwo.user.application.port.out.UserGenericJpaRepo;
import cord.eoeo.momentwo.user.domain.OAuthInfo;
import cord.eoeo.momentwo.user.domain.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserFindOAuthJpaRepo extends UserGenericJpaRepo {
    @Query("SELECT u FROM User u WHERE u.username = :username AND u.oAuthInfo = :oAuthInfo")
    Optional<User> findByUsernameAndOAuthInfo(String username, OAuthInfo oAuthInfo);
}
