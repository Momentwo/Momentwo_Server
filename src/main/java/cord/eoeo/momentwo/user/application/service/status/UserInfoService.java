package cord.eoeo.momentwo.user.application.service.status;

import cord.eoeo.momentwo.user.adapter.dto.out.LoginInfoResponse;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.in.status.UserInfoUseCase;
import cord.eoeo.momentwo.user.application.port.out.find.UserFindUsernameRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService implements UserInfoUseCase {
    private final UserFindUsernameRepo userFindUsernameRepo;

    @Override
    public LoginInfoResponse getUserInfo(String username) {
        User user = userFindUsernameRepo.findByUsername(username).orElseThrow(NotFoundUserException::new);
        return new LoginInfoResponse().toDo(user);
    }
}
