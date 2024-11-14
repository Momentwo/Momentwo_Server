package cord.eoeo.momentwo.user.application.port.out.valid;

import cord.eoeo.momentwo.user.domain.User;

public interface UserNicknameValidPort {
    User authenticationValid();

    User userNicknameValid(String nickname);
}
