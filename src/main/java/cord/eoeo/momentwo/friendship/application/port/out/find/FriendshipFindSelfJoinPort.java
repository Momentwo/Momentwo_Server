package cord.eoeo.momentwo.friendship.application.port.out.find;

import cord.eoeo.momentwo.friendship.domain.Friendship;
import cord.eoeo.momentwo.user.domain.User;

import java.util.Optional;

public interface FriendshipFindSelfJoinPort {
    Optional<Friendship> findBySelfJoin(User fromUser, User toUser);
}
