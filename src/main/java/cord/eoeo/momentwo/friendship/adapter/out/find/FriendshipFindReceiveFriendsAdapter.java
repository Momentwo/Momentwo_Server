package cord.eoeo.momentwo.friendship.adapter.out.find;

import cord.eoeo.momentwo.friendship.application.port.out.find.FriendshipFindReceiveFriendsPort;
import cord.eoeo.momentwo.friendship.application.port.out.jpa.FriendshipFindJpaRepo;
import cord.eoeo.momentwo.friendship.domain.Friendship;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FriendshipFindReceiveFriendsAdapter implements FriendshipFindReceiveFriendsPort {
    private final FriendshipFindJpaRepo friendshipFindJpaRepo;

    @Override
    public List<Friendship> findReceiveFriendsByUser(User owner) {
        return friendshipFindJpaRepo.findReceiveFriendsByUser(owner);
    }
}
