package cord.eoeo.momentwo.user.application.port.out.find;

import cord.eoeo.momentwo.user.domain.User;

import java.util.Optional;

public interface UserFindUsernameAndPhoneRepo {
    Optional<User> findByUsernameAndPhone(String username, String phone);
}
