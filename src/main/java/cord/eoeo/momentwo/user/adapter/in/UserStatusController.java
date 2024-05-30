package cord.eoeo.momentwo.user.adapter.in;

import cord.eoeo.momentwo.config.security.jwt.adapter.out.TokenResponseDto;
import cord.eoeo.momentwo.user.adapter.dto.in.UserLoginRequestDto;
import cord.eoeo.momentwo.user.application.port.in.UserStatusUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserStatusController {

    private final UserStatusUseCase userStatusUseCase;

    // 로그인
    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponseDto signIn(@RequestBody @Valid UserLoginRequestDto userLoginRequestDto) {
        return userStatusUseCase.signIn(userLoginRequestDto);
    }

    // 로그아웃
    @PostMapping("/signout")
    @ResponseStatus(HttpStatus.OK)
    public void signOut(@RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        userStatusUseCase.blackListToken(jwtToken);
    }
}
