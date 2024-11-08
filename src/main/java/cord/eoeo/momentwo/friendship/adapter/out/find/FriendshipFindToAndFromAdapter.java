package cord.eoeo.momentwo.friendship.adapter.out.find;

import cord.eoeo.momentwo.friendship.application.port.out.find.FriendshipFindToAndFromPort;
import cord.eoeo.momentwo.friendship.application.port.out.jpa.FriendshipFindJpaRepo;
import cord.eoeo.momentwo.friendship.domain.Friendship;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FriendshipFindToAndFromAdapter implements FriendshipFindToAndFromPort {
    private final FriendshipFindJpaRepo friendshipFindJpaRepo;

    @Override
    public Friendship findByToUserAndFromUser(User toUser, User fromUser) {
        return friendshipFindJpaRepo.findByToUserAndFromUser(toUser, fromUser);
    }
}
