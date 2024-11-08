package cord.eoeo.momentwo.friendship.adapter.out.find;

import cord.eoeo.momentwo.friendship.application.port.out.find.FriendshipFindSelfJoinPort;
import cord.eoeo.momentwo.friendship.application.port.out.jpa.FriendshipFindJpaRepo;
import cord.eoeo.momentwo.friendship.domain.Friendship;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FriendshipFindSelfJoinAdapter implements FriendshipFindSelfJoinPort {
    private final FriendshipFindJpaRepo friendshipFindJpaRepo;

    @Override
    public Optional<Friendship> findBySelfJoin(User fromUser, User toUser) {
        return friendshipFindJpaRepo.findBySelfJoin(fromUser, toUser);
    }
}
