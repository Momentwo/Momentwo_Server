package cord.eoeo.momentwo.user.application.port.out.jpa;

import cord.eoeo.momentwo.user.domain.RefreshToken;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RefreshTokenFindJpaRepoExtend extends RefreshTokenFindJpaRepo {
    @Query("SELECT r FROM RefreshToken r WHERE r.nickname = :nickname")
    Optional<RefreshToken> findByNickname(String nickname);
}
