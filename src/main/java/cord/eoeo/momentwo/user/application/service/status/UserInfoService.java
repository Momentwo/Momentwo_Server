package cord.eoeo.momentwo.user.application.service.status;

import cord.eoeo.momentwo.user.adapter.dto.out.LoginInfoResponse;
import cord.eoeo.momentwo.user.application.port.in.status.UserInfoUseCase;
import cord.eoeo.momentwo.user.application.port.out.valid.UsernameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService implements UserInfoUseCase {
    private final UsernameValidPort usernameValidPort;

    @Override
    public LoginInfoResponse getUserInfo(String username) {
        User user = usernameValidPort.usernameValid(username);

        return new LoginInfoResponse().toDo(user);
    }
}
