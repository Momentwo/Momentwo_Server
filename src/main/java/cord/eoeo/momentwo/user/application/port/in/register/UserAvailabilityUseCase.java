package cord.eoeo.momentwo.user.application.port.in.register;

import cord.eoeo.momentwo.user.adapter.dto.in.EmailAvailabilityDto;
import cord.eoeo.momentwo.user.adapter.dto.in.NicknameAvailabilityDto;

import java.util.concurrent.CompletableFuture;

public interface UserAvailabilityUseCase {
    /**
     * 지속적인 이메일 중복 유효성 확인
     * @param emailAvailabilityDto
     * username : 이메일
     * @return : void
     */
    CompletableFuture<Void> checkEmailAvailability(EmailAvailabilityDto emailAvailabilityDto);

    /**
     * 지속적인 별명 중복 유효성 확인
     * @param nicknameAvailabilityDto
     * nickname : 별명
     * @return : void
     */
    CompletableFuture<Void> checkNicknameAvailability(NicknameAvailabilityDto nicknameAvailabilityDto);
}
