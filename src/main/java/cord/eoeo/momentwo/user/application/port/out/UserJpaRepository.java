package cord.eoeo.momentwo.user.application.port.out;

import cord.eoeo.momentwo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> existsByUsername(String username);

    Optional<User> existsByNickname(String nickname);
}
