package cord.eoeo.momentwo.user.adapter.out;

import cord.eoeo.momentwo.user.application.port.out.UserJpaRepository;
import cord.eoeo.momentwo.user.application.port.out.UserRepository;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public void save(User user) {
        userJpaRepository.save(user);
    }

    @Override
    public Optional<User> findById(long id) {
        return userJpaRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username);
    }

    @Override
    public void delete(User user) {
        userJpaRepository.delete(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userJpaRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return userJpaRepository.existsByNickname(nickname);
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        return userJpaRepository.findByNickname(nickname);
    }

    @Override
    public Optional<User> findByNameAndPhone(String name, String phone) {
        return userJpaRepository.findByNameAndPhone(name, phone);
    }

    @Override
    public Optional<User> findByUsernameAndPhone(String username, String phone) {
        return userJpaRepository.findByUsernameAndPhone(username, phone);
    }
}
