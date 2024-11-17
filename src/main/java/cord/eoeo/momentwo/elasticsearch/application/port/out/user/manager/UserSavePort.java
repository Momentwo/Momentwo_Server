package cord.eoeo.momentwo.elasticsearch.application.port.out.user.manager;

import cord.eoeo.momentwo.user.domain.User;

public interface UserSavePort {
    void userSave(User user);
}
