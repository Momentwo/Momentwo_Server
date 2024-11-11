package cord.eoeo.momentwo.user.application.port.out.find;

import cord.eoeo.momentwo.user.domain.RefreshToken;

import java.util.Optional;

public interface RefreshTokenFindByNicknameRepo {
    Optional<RefreshToken> findByNickname(String nickname);
}
