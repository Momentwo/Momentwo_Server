package cord.eoeo.momentwo.user.application.port.out.jpa;

import cord.eoeo.momentwo.user.application.port.out.UserGenericJpaRepo;
import cord.eoeo.momentwo.user.domain.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserFindJpaRepo extends UserGenericJpaRepo {
    Optional<User> findByUsername(String username);

    Optional<User> findByNickname(String nickname);

    @Query("SELECT u FROM User u WHERE u.name = :name AND u.phone = :phone")
    Optional<User> findByNameAndPhone(String name, String phone);

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.phone = :phone")
    Optional<User> findByUsernameAndPhone(String username, String phone);
}
