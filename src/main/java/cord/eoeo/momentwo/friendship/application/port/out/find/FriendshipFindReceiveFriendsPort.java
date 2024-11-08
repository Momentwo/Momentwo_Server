package cord.eoeo.momentwo.friendship.application.port.out.find;

import cord.eoeo.momentwo.friendship.domain.Friendship;
import cord.eoeo.momentwo.user.domain.User;

import java.util.List;

public interface FriendshipFindReceiveFriendsPort {
    List<Friendship> findReceiveFriendsByUser(User owner);
}
