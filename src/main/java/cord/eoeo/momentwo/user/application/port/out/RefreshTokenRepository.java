package cord.eoeo.momentwo.user.application.port.out;

import cord.eoeo.momentwo.user.domain.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository {
    Optional<RefreshToken> findByRefreshToken(String token);
    void save(RefreshToken refreshToken);
}
