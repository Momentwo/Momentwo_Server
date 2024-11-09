package cord.eoeo.momentwo.user.application.port.out.jpa;

import cord.eoeo.momentwo.user.application.port.out.RefreshTokenGenericJpaRepo;
import cord.eoeo.momentwo.user.domain.RefreshToken;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RefreshTokenFindJpaRepo extends RefreshTokenGenericJpaRepo {
    @Query("SELECT r FROM RefreshToken r WHERE r.token = :token")
    Optional<RefreshToken> findByRefreshToken(String token);
}
