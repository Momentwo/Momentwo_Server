package cord.eoeo.momentwo.user.application.port.in;

import cord.eoeo.momentwo.config.security.jwt.adapter.out.TokenResponseDto;
import cord.eoeo.momentwo.user.adapter.dto.in.UserLoginRequestDto;

public interface UserStatusUseCase {
    TokenResponseDto signIn(UserLoginRequestDto userLoginRequestDto);
    void blackListToken(String token);
}
