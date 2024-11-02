package cord.eoeo.momentwo.user.adapter.in.info;

import cord.eoeo.momentwo.user.adapter.dto.in.TempPasswordRequestDto;
import cord.eoeo.momentwo.user.adapter.dto.out.TempPasswordResponseDto;
import cord.eoeo.momentwo.user.application.port.in.password.UserPasswordTempUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class TempPasswordController {
    private final UserPasswordTempUseCase userPasswordTempUseCase;

    // 임시 비밀번호 발급 -> 비밀번호 찾기 시 적용
    @PostMapping("/temp/password")
    @ResponseStatus(HttpStatus.OK)
    public TempPasswordResponseDto tempPassword(@RequestBody @Valid TempPasswordRequestDto tempPasswordRequestDto) {
        return userPasswordTempUseCase.tempPassword(tempPasswordRequestDto);
    }
}
