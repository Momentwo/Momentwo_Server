package cord.eoeo.momentwo.user.application.port.in.status;

import cord.eoeo.momentwo.config.security.jwt.adapter.out.TokenResponseDto;
import cord.eoeo.momentwo.user.adapter.dto.in.UserLoginRequestDto;

public interface UserSignInUseCase {
    /**
     * 로그인
     * @param userLoginRequestDto
     * username : 이메일
     * password : 비밀번호
     * @return : 토큰 발행
     */
    TokenResponseDto signIn(UserLoginRequestDto userLoginRequestDto);
}
