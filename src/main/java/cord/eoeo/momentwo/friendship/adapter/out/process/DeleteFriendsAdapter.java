package cord.eoeo.momentwo.friendship.adapter.out.process;

import cord.eoeo.momentwo.friendship.advice.exception.NotDeleteFriendsException;
import cord.eoeo.momentwo.friendship.application.port.out.delete.FriendshipDeleteToAndFromAndAcceptPort;
import cord.eoeo.momentwo.friendship.application.port.out.process.DeleteFriendsPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteFriendsAdapter implements DeleteFriendsPort {
    private final FriendshipDeleteToAndFromAndAcceptPort friendshipDeleteToAndFromAndAcceptPort;

    @Override
    public void deleteFriends(User toUser, User fromUser) {
        if(!friendshipDeleteToAndFromAndAcceptPort.deleteByToUserAndFromUser(toUser, fromUser)) {
            throw new NotDeleteFriendsException();
        }
    }
}
