package cord.eoeo.momentwo.user.application.port.out.delete;

import cord.eoeo.momentwo.user.domain.User;

public interface UserDeleteRepo {
    void delete(User user);
}
