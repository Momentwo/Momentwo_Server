package cord.eoeo.momentwo.friendship.application.port.out;

import cord.eoeo.momentwo.user.domain.User;

public interface FriendshipProcess {
    void friendsRequest(User from, User to, boolean type);

    void responseProcess(User responseUser, User requestUser, Boolean accept);

    boolean isRequestFriends(User from, User to);

    void requestCancel(User from, User to);
}
