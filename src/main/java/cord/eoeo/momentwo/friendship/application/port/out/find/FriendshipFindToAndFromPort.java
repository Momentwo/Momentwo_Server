package cord.eoeo.momentwo.friendship.application.port.out.find;

import cord.eoeo.momentwo.friendship.domain.Friendship;
import cord.eoeo.momentwo.user.domain.User;

public interface FriendshipFindToAndFromPort {
    Friendship findByToUserAndFromUser(User toUser, User fromUser);
}
