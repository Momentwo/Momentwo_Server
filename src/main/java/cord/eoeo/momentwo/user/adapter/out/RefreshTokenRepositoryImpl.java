package cord.eoeo.momentwo.user.adapter.out;

import cord.eoeo.momentwo.user.application.port.out.RefreshTokenJpaRepository;
import cord.eoeo.momentwo.user.application.port.out.RefreshTokenRepository;
import cord.eoeo.momentwo.user.domain.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {
    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    @Override
    public Optional<RefreshToken> findByRefreshToken(String token) {
        return refreshTokenJpaRepository.findByRefreshToken(token);
    }

    @Override
    public void save(RefreshToken refreshToken) {
        refreshTokenJpaRepository.save(refreshToken);
    }
}
