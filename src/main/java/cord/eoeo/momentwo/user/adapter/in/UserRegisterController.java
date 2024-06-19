package cord.eoeo.momentwo.user.adapter.in;

import cord.eoeo.momentwo.user.adapter.dto.in.EmailAvailabilityDto;
import cord.eoeo.momentwo.user.adapter.dto.in.NicknameAvailabilityDto;
import cord.eoeo.momentwo.user.adapter.dto.in.UserRegisterRequestDto;
import cord.eoeo.momentwo.user.application.port.in.UserRegisterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class UserRegisterController {
    private final UserRegisterUseCase userRegisterUseCase;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public void register(@RequestBody @Valid UserRegisterRequestDto userRegisterRequestDto) {
        userRegisterUseCase.register(userRegisterRequestDto);
    }

    @PostMapping("/check_email")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<Void> checkEmailAvailability(@RequestBody @Valid EmailAvailabilityDto emailAvailabilityDto) {
        return userRegisterUseCase.checkEmailAvailability(emailAvailabilityDto);
    }

    @PostMapping("/check_nickname")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<Void> checkNicknameAvailability(@RequestBody @Valid NicknameAvailabilityDto nicknameAvailabilityDto) {
        return userRegisterUseCase.checkNicknameAvailability(nicknameAvailabilityDto);
    }
}
