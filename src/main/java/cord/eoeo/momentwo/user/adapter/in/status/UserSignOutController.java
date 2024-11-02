package cord.eoeo.momentwo.user.adapter.in.status;

import cord.eoeo.momentwo.user.adapter.dto.in.SignOutRequestDto;
import cord.eoeo.momentwo.user.application.port.in.status.UserSignOutUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserSignOutController {
    private final UserSignOutUseCase userSignOutUseCase;

    // 회원 탈퇴
    @DeleteMapping("/signout")
    @ResponseStatus(HttpStatus.OK)
    public void signOut(@ModelAttribute @Valid SignOutRequestDto signOutRequestDto) {
        userSignOutUseCase.signOut(signOutRequestDto);
    }
}
