package cord.eoeo.momentwo.user.application.port.in;

import cord.eoeo.momentwo.user.adapter.dto.in.UserRegisterRequestDto;

public interface UserRegisterUseCase {
    void register(UserRegisterRequestDto userRegisterRequestDto);
}
