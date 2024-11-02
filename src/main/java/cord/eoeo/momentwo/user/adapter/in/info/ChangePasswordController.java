package cord.eoeo.momentwo.user.adapter.in.info;

import cord.eoeo.momentwo.user.adapter.dto.in.ChangePasswordRequestDto;
import cord.eoeo.momentwo.user.adapter.dto.in.SearchUsernameRequestDto;
import cord.eoeo.momentwo.user.adapter.dto.out.SearchUsernameResponseDto;
import cord.eoeo.momentwo.user.application.port.in.password.UserPasswordChangeUseCase;
import cord.eoeo.momentwo.user.application.port.in.search.UsernameSearchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ChangePasswordController {
    private final UserPasswordChangeUseCase userPasswordChangeUseCase;

    // 비밀번호 변경
    @PostMapping("/change/password")
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(@RequestBody @Valid ChangePasswordRequestDto changePasswordRequestDto) {
        userPasswordChangeUseCase.changePassword(changePasswordRequestDto);
    }
}
