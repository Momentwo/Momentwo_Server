package cord.eoeo.momentwo.user.application.service.blacklist;

import cord.eoeo.momentwo.config.security.jwt.port.out.JWTBlackList;
import cord.eoeo.momentwo.user.application.port.in.blacklist.UserBlackListTokenUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBlackListTokenService implements UserBlackListTokenUseCase {
    private final JWTBlackList jwtBlackList;

    @Override
    public void blackListToken(String token) {
        jwtBlackList.blackListToken(token);
    }
}
