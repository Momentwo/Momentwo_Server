package cord.eoeo.momentwo.user.adapter.in.status;

import cord.eoeo.momentwo.config.security.jwt.adapter.out.TokenResponseDto;
import cord.eoeo.momentwo.user.adapter.dto.in.UserLoginRequestDto;
import cord.eoeo.momentwo.user.application.port.in.status.UserSignInUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserLoginController {
    private final String AUTHENTICATION_KEY = "Authorization";
    private final String REFRESH_KEY = "Refresh";
    private final UserSignInUseCase userSignInUseCase;

    // 로그인
    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public void signIn(@RequestBody @Valid UserLoginRequestDto userLoginRequestDto,
                       HttpServletResponse httpServletResponse) {
        TokenResponseDto tokenResponseDto = userSignInUseCase.signIn(userLoginRequestDto);
        httpServletResponse.setHeader(AUTHENTICATION_KEY, tokenResponseDto.getAccessToken());
        httpServletResponse.setHeader(REFRESH_KEY, tokenResponseDto.getRefreshToken());
    }
}
