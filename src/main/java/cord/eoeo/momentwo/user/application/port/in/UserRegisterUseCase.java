package cord.eoeo.momentwo.user.application.port.in;

import cord.eoeo.momentwo.user.adapter.dto.in.EmailAvailabilityDto;
import cord.eoeo.momentwo.user.adapter.dto.in.NicknameAvailabilityDto;
import cord.eoeo.momentwo.user.adapter.dto.in.UserRegisterRequestDto;

import java.util.concurrent.CompletableFuture;

public interface UserRegisterUseCase {
    void register(UserRegisterRequestDto userRegisterRequestDto);

    // 지속적인 이메일 중복 유효성 확인
    CompletableFuture<Void> checkEmailAvailability(EmailAvailabilityDto emailAvailabilityDto);

    // 지속적인 별명 중복 유효성 확인
    CompletableFuture<Void> checkNicknameAvailability(NicknameAvailabilityDto nicknameAvailabilityDto);
}
