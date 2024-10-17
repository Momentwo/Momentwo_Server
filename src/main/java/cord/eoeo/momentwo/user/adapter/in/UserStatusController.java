package cord.eoeo.momentwo.user.adapter.in;

import cord.eoeo.momentwo.config.security.jwt.adapter.out.TokenResponseDto;
import cord.eoeo.momentwo.user.adapter.dto.in.SignOutRequestDto;
import cord.eoeo.momentwo.user.adapter.dto.in.UserLoginRequestDto;
import cord.eoeo.momentwo.user.application.port.in.UserStatusUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserStatusController {

    private final String AUTHENTICATION_KEY = "Authorization";

    private final String REFRESH_KEY = "Refresh";

    private final UserStatusUseCase userStatusUseCase;

    // 로그인
    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public void signIn(@RequestBody @Valid UserLoginRequestDto userLoginRequestDto,
                       HttpServletResponse httpServletResponse) {
        TokenResponseDto tokenResponseDto = userStatusUseCase.signIn(userLoginRequestDto).join();
        httpServletResponse.setHeader(AUTHENTICATION_KEY, tokenResponseDto.getAccessToken());
        httpServletResponse.setHeader(REFRESH_KEY, tokenResponseDto.getRefreshToken());
    }

    // 로그아웃
    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(@RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        userStatusUseCase.blackListToken(jwtToken);
    }

    // 회원 탈퇴
    @DeleteMapping("/signout")
    @ResponseStatus(HttpStatus.OK)
    public void signOut(@ModelAttribute @Valid SignOutRequestDto signOutRequestDto) {
        userStatusUseCase.signOut(signOutRequestDto);
    }
}
