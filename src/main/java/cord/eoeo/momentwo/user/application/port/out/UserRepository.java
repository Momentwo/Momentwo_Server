package cord.eoeo.momentwo.user.application.port.out;

import cord.eoeo.momentwo.user.domain.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);

    Optional<User> findById(long id);

    Optional<User> findByUsername(String username);

    void delete(User user);

    Optional<User> existsByUsername(String username);

    Optional<User> existsByNickname(String nickname);
}
