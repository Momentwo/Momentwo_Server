package cord.eoeo.momentwo.user.adapter.out;

import cord.eoeo.momentwo.user.application.port.out.UserGenericJpaRepo;
import cord.eoeo.momentwo.user.application.port.out.UserGenericRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class UserGenericRepoAdapter implements UserGenericRepo {
    private final UserGenericJpaRepo userGenericJpaRepo;

    @Override
    public void save(User entity) {
        userGenericJpaRepo.save(entity);
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return userGenericJpaRepo.findById(aLong);
    }

    @Override
    public List<User> findAll() {
        return userGenericJpaRepo.findAll();
    }

    @Override
    public void deleteById(Long aLong) {
        userGenericJpaRepo.deleteById(aLong);
    }
}
