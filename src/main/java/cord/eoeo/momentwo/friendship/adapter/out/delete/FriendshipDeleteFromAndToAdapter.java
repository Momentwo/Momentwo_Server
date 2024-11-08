package cord.eoeo.momentwo.friendship.adapter.out.delete;

import cord.eoeo.momentwo.friendship.application.port.out.delete.FriendshipDeleteFromAndToPort;
import cord.eoeo.momentwo.friendship.application.port.out.jpa.FriendshipDeleteJpaRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FriendshipDeleteFromAndToAdapter implements FriendshipDeleteFromAndToPort {
    private final FriendshipDeleteJpaRepo friendshipDeleteJpaRepo;

    @Override
    public void deleteByFromUserAndToUser(User fromUser, User toUser) {
        friendshipDeleteJpaRepo.deleteByFromUserAndToUser(fromUser.getId(), toUser.getId());
    }
}
