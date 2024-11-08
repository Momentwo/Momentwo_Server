package cord.eoeo.momentwo.friendship.adapter.out.delete;

import cord.eoeo.momentwo.friendship.application.port.out.delete.FriendshipDeleteToAndFromAndAcceptPort;
import cord.eoeo.momentwo.friendship.application.port.out.jpa.FriendshipDeleteJpaRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FriendshipDeleteToAndFromAndAcceptAdapter implements FriendshipDeleteToAndFromAndAcceptPort {
    private final FriendshipDeleteJpaRepo friendshipDeleteJpaRepo;

    @Override
    public boolean deleteByToUserAndFromUser(User toUser, User fromUser) {
        int isDelete = friendshipDeleteJpaRepo.deleteByToUserAndFromUser(toUser, fromUser);
        System.out.println(isDelete);
        return isDelete > 0;
    }
}
