package cord.eoeo.momentwo.user.application.port.in.profile;

import cord.eoeo.momentwo.user.adapter.dto.out.UserProfileResponseDto;

public interface UserProfileUseCase {
    UserProfileResponseDto getUserProfile(String nickname);
}
