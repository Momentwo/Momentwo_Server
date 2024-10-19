package cord.eoeo.momentwo.user.application.port.in;

import cord.eoeo.momentwo.config.security.jwt.adapter.out.TokenResponseDto;
import cord.eoeo.momentwo.user.adapter.dto.in.SignOutRequestDto;
import cord.eoeo.momentwo.user.adapter.dto.in.UserLoginRequestDto;

import java.util.concurrent.CompletableFuture;

public interface UserStatusUseCase {
    CompletableFuture<TokenResponseDto> signIn(UserLoginRequestDto userLoginRequestDto);
    void blackListToken(String token);
    void signOut(SignOutRequestDto signOutRequestDto);
}
