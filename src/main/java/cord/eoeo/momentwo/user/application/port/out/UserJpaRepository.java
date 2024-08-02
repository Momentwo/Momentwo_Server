package cord.eoeo.momentwo.user.application.port.out;

import cord.eoeo.momentwo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByNickname(String nickname);

    Optional<User> findByNickname(String nickname);

    @Query("SELECT u FROM User u WHERE u.name = :name AND u.phone = :phone")
    Optional<User> findByNameAndPhone(String name, String phone);

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.phone = :phone")
    Optional<User> findByUsernameAndPhone(String username, String phone);
}
