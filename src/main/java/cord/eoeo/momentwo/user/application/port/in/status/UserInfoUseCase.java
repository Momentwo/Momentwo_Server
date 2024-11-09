package cord.eoeo.momentwo.user.application.port.in.status;

import cord.eoeo.momentwo.user.adapter.dto.out.LoginInfoResponse;

public interface UserInfoUseCase {
    LoginInfoResponse getUserInfo(String username);
}
