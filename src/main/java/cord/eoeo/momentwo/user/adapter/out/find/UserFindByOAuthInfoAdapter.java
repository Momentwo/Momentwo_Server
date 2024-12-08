package cord.eoeo.momentwo.user.adapter.out.find;

import cord.eoeo.momentwo.user.application.port.out.find.UserFindByOAuthInfoRepo;
import cord.eoeo.momentwo.user.application.port.out.jpa.UserFindOAuthJpaRepo;
import cord.eoeo.momentwo.user.domain.OAuthInfo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserFindByOAuthInfoAdapter implements UserFindByOAuthInfoRepo {
    private final UserFindOAuthJpaRepo userFindOAuthJpaRepo;

    @Override
    public Optional<User> userFindByOAuthInfo(String username, OAuthInfo oAuthInfo) {
        return userFindOAuthJpaRepo.findByUsernameAndOAuthInfo(username, oAuthInfo);
    }
}
