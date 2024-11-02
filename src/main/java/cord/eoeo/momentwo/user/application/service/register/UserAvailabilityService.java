package cord.eoeo.momentwo.user.application.service.register;

import cord.eoeo.momentwo.user.adapter.dto.in.EmailAvailabilityDto;
import cord.eoeo.momentwo.user.adapter.dto.in.NicknameAvailabilityDto;
import cord.eoeo.momentwo.user.application.port.in.register.UserAvailabilityUseCase;
import cord.eoeo.momentwo.user.application.port.out.UserRegisterAsync;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class UserAvailabilityService implements UserAvailabilityUseCase {
    private final UserRegisterAsync userRegisterAsync;

    @Override
    @Transactional(readOnly = true)
    public CompletableFuture<Void> checkEmailAvailability(EmailAvailabilityDto emailAvailabilityDto) {
        return userRegisterAsync.checkUsernameDuplicate(emailAvailabilityDto.getUsername());
    }

    @Override
    @Transactional(readOnly = true)
    public CompletableFuture<Void> checkNicknameAvailability(NicknameAvailabilityDto nicknameAvailabilityDto) {
        return userRegisterAsync.checkUserNicknameDuplicate(nicknameAvailabilityDto.getNickname());
    }
}
