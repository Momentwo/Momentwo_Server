package cord.eoeo.momentwo.user.adapter.out.delete;

import cord.eoeo.momentwo.user.application.port.out.delete.UserDeleteRepo;
import cord.eoeo.momentwo.user.application.port.out.jpa.UserDeleteJpaRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserDeleteAdapter implements UserDeleteRepo {
    private final UserDeleteJpaRepo userDeleteJpaRepo;

    @Override
    public void delete(User user) {
        userDeleteJpaRepo.delete(user);
    }
}
