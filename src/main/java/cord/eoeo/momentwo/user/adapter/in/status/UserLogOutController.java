package cord.eoeo.momentwo.user.adapter.in.status;

import cord.eoeo.momentwo.user.application.port.in.blacklist.UserBlackListTokenUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserLogOutController {
    private final UserBlackListTokenUseCase userBlackListTokenUseCase;

    // 로그아웃
    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(@RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        userBlackListTokenUseCase.blackListToken(jwtToken);
    }
}
