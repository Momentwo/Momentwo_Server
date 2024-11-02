package cord.eoeo.momentwo.user.adapter.out.find;

import cord.eoeo.momentwo.user.application.port.out.find.RefreshTokenFindTokenRepo;
import cord.eoeo.momentwo.user.application.port.out.jpa.RefreshTokenFindJpaRepo;
import cord.eoeo.momentwo.user.domain.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class RefreshTokenFindTokenAdapter implements RefreshTokenFindTokenRepo {
    private final RefreshTokenFindJpaRepo refreshTokenFindJpaRepo;

    @Override
    public Optional<RefreshToken> findByRefreshToken(String token) {
        return refreshTokenFindJpaRepo.findByRefreshToken(token);
    }
}
