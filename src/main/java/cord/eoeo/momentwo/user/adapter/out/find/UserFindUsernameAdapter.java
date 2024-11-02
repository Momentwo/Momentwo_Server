package cord.eoeo.momentwo.user.adapter.out.find;

import cord.eoeo.momentwo.user.application.port.out.find.UserFindUsernameRepo;
import cord.eoeo.momentwo.user.application.port.out.jpa.UserFindJpaRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class UserFindUsernameAdapter implements UserFindUsernameRepo {
    private final UserFindJpaRepo userFindJpaRepo;

    @Override
    public Optional<User> findByUsername(String username) {
        return userFindJpaRepo.findByUsername(username);
    }
}
