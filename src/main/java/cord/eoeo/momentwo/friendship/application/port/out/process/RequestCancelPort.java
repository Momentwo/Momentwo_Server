package cord.eoeo.momentwo.friendship.application.port.out.process;

import cord.eoeo.momentwo.user.domain.User;

public interface RequestCancelPort {
    void requestCancel(User from, User to);
}
