package cord.eoeo.momentwo.friendship.application.port.out.delete;

import cord.eoeo.momentwo.user.domain.User;

public interface FriendshipDeleteToAndFromAndAcceptPort {
    boolean deleteByToUserAndFromUser(User toUser, User fromUser);
}
