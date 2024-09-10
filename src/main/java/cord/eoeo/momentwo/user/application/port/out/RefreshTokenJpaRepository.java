package cord.eoeo.momentwo.user.application.port.out;

import cord.eoeo.momentwo.user.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenJpaRepository extends JpaRepository<RefreshToken, Long> {
    @Query("SELECT r FROM RefreshToken r WHERE r.token = :token")
    Optional<RefreshToken> findByRefreshToken(String token);
}
