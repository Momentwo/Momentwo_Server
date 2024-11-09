package cord.eoeo.momentwo.user.adapter.in.register;

import cord.eoeo.momentwo.user.adapter.dto.in.EmailAvailabilityDto;
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
public class EmailRegisterCheckController {
    private final UserAvailabilityUseCase userAvailabilityUseCase;

    @PostMapping("/check_email")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<Void> checkEmailAvailability(@ModelAttribute @Valid EmailAvailabilityDto emailAvailabilityDto) {
        return userAvailabilityUseCase.checkEmailAvailability(emailAvailabilityDto);
    }
}
