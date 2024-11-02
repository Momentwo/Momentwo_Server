package cord.eoeo.momentwo.user.adapter.in.register;

import cord.eoeo.momentwo.user.adapter.dto.in.UserRegisterRequestDto;
import cord.eoeo.momentwo.user.application.port.in.register.UserRegisterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserRegisterController {
    private final UserRegisterUseCase userRegisterUseCase;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public void register(@RequestBody @Valid UserRegisterRequestDto userRegisterRequestDto) {
        userRegisterUseCase.register(userRegisterRequestDto);
    }
}
