package cord.eoeo.momentwo.friendship.application.port.out.process;

import cord.eoeo.momentwo.user.domain.User;

public interface ResponseProcessPort {
    void responseProcess(User responseUser, User requestUser, Boolean accept);
}
