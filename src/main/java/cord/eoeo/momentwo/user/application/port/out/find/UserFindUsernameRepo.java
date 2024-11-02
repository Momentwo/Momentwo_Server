package cord.eoeo.momentwo.user.application.port.out.find;

import cord.eoeo.momentwo.user.domain.User;

import java.util.Optional;

public interface UserFindUsernameRepo {
    Optional<User> findByUsername(String username);
}
