package cord.eoeo.momentwo.user.adapter.out.oauth;

import cord.eoeo.momentwo.user.application.port.out.jpa.UserExistsJpaRepo;
import cord.eoeo.momentwo.user.application.port.out.oauth.IsUsernameDuplicatePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IsUsernameDuplicateAdapter implements IsUsernameDuplicatePort {
    private final UserExistsJpaRepo userExistsJpaRepo;

    @Override
    public boolean isUsernameDuplicate(String username) {
        return userExistsJpaRepo.existsByUsername(username);
    }
}
