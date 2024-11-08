package cord.eoeo.momentwo.friendship.application.port.out.process;

import cord.eoeo.momentwo.user.domain.User;

public interface DeleteFriendsPort {
    void deleteFriends(User toUser, User fromUser);
}
