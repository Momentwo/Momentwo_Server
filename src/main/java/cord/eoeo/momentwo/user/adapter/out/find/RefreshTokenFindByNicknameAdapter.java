package cord.eoeo.momentwo.user.adapter.out.find;

import cord.eoeo.momentwo.user.application.port.out.find.RefreshTokenFindByNicknameRepo;
import cord.eoeo.momentwo.user.application.port.out.jpa.RefreshTokenFindJpaRepoExtend;
import cord.eoeo.momentwo.user.domain.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RefreshTokenFindByNicknameAdapter implements RefreshTokenFindByNicknameRepo {
    private final RefreshTokenFindJpaRepoExtend refreshTokenFindJpaRepoExtend;

    @Override
    public Optional<RefreshToken> findByNickname(String nickname) {
        return refreshTokenFindJpaRepoExtend.findByNickname(nickname);
    }
}
