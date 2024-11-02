package cord.eoeo.momentwo.user.adapter.in.register;

import cord.eoeo.momentwo.user.adapter.dto.in.NicknameAvailabilityDto;
import cord.eoeo.momentwo.user.application.port.in.register.UserAvailabilityUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class NicknameRegisterCheckController {
    private final UserAvailabilityUseCase userAvailabilityUseCase;

    @PostMapping("/check_nickname")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<Void> checkNicknameAvailability(@ModelAttribute @Valid NicknameAvailabilityDto nicknameAvailabilityDto) {
        return userAvailabilityUseCase.checkNicknameAvailability(nicknameAvailabilityDto);
    }
}
