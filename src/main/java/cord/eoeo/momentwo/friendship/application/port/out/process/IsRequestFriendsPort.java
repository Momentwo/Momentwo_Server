package cord.eoeo.momentwo.friendship.application.port.out.process;

import cord.eoeo.momentwo.user.domain.User;

public interface IsRequestFriendsPort {
    boolean isRequestFriends(User from, User to);
}
