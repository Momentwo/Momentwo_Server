package cord.eoeo.momentwo.user.adapter.in.info;

import cord.eoeo.momentwo.user.adapter.dto.out.UserProfileResponseDto;
import cord.eoeo.momentwo.user.application.port.in.profile.UserProfileUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileUseCase userProfileUseCase;

    @GetMapping("/users/profiles")
    @ResponseStatus(HttpStatus.OK)
    public UserProfileResponseDto getUserProfile(@RequestParam String nickname) {
        return userProfileUseCase.getUserProfile(nickname);
    }
}
