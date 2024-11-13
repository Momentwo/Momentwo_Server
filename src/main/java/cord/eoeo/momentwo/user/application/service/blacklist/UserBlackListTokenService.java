package cord.eoeo.momentwo.user.application.service.blacklist;

import cord.eoeo.momentwo.config.security.jwt.port.out.JWTBlackList;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.in.blacklist.UserBlackListTokenUseCase;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.application.port.out.RefreshTokenGenericRepo;
import cord.eoeo.momentwo.user.application.port.out.find.RefreshTokenFindByNicknameRepo;
import cord.eoeo.momentwo.user.application.port.out.find.UserFindNicknameRepo;
import cord.eoeo.momentwo.user.domain.RefreshToken;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserBlackListTokenService implements UserBlackListTokenUseCase {
    private final JWTBlackList jwtBlackList;
    private final RefreshTokenGenericRepo refreshTokenGenericRepo;
    private final UserFindNicknameRepo userFindNicknameRepo;
    private final GetAuthentication getAuthentication;
    private final RefreshTokenFindByNicknameRepo refreshTokenFindByNicknameRepo;

    @Override
    @Transactional
    public void blackListToken(String token) {
        // 유저 정보 조회 및 RT 삭제
        User user = userFindNicknameRepo.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);
        RefreshToken refreshToken = refreshTokenFindByNicknameRepo.findByNickname(user.getUsername())
                .orElseThrow(NotFoundUserException::new);

        refreshTokenGenericRepo.deleteById(refreshToken.getId());

        // 블랙리스트 토큰 담기
        jwtBlackList.blackListToken(token);
    }
}
