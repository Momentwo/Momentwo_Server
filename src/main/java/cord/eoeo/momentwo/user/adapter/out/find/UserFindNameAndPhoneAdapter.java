package cord.eoeo.momentwo.user.adapter.out.find;

import cord.eoeo.momentwo.user.application.port.out.find.UserFindNameAndPhoneRepo;
import cord.eoeo.momentwo.user.application.port.out.jpa.UserFindJpaRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class UserFindNameAndPhoneAdapter implements UserFindNameAndPhoneRepo {
    private final UserFindJpaRepo userFindJpaRepo;

    @Override
    public Optional<User> findByNameAndPhone(String name, String phone) {
        return userFindJpaRepo.findByNameAndPhone(name, phone);
    }
}
