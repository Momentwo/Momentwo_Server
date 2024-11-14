package cord.eoeo.momentwo.user.application.port.out.valid;


import cord.eoeo.momentwo.user.domain.User;

public interface UsernameValidPort {
    User usernameValid(String username);
}
