package cord.eoeo.momentwo.friendship.adapter.out.find;

import cord.eoeo.momentwo.friendship.application.port.out.find.FriendshipFindToAndFromAndAcceptPort;
import cord.eoeo.momentwo.friendship.application.port.out.jpa.FriendshipFindJpaRepo;
import cord.eoeo.momentwo.friendship.domain.Friendship;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FriendshipFindToAndFromAndAcceptAdapter implements FriendshipFindToAndFromAndAcceptPort {
    private final FriendshipFindJpaRepo friendshipFindJpaRepo;

    @Override
    public Optional<Friendship> findByToUserAndFromUserAndAccept(User toUser, User fromUser, boolean accept) {
        return friendshipFindJpaRepo.findByToUserAndFromUserAndAccept(toUser.getId(), fromUser.getId(), accept);
    }
}
