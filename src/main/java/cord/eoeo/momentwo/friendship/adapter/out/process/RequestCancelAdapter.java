package cord.eoeo.momentwo.friendship.adapter.out.process;

import cord.eoeo.momentwo.friendship.advice.exception.NotFoundFriendshipRequestException;
import cord.eoeo.momentwo.friendship.application.port.out.delete.FriendshipDeleteFromAndToPort;
import cord.eoeo.momentwo.friendship.application.port.out.find.FriendshipFindSelfJoinPort;
import cord.eoeo.momentwo.friendship.application.port.out.process.RequestCancelPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestCancelAdapter implements RequestCancelPort {
    private final FriendshipFindSelfJoinPort friendshipFindSelfJoinPort;
    private final FriendshipDeleteFromAndToPort friendshipDeleteFromAndToPort;

    @Override
    public void requestCancel(User fromUser, User toUser) {
        friendshipFindSelfJoinPort.findBySelfJoin(fromUser, toUser).orElseThrow(NotFoundFriendshipRequestException::new);
        friendshipDeleteFromAndToPort.deleteByFromUserAndToUser(fromUser, toUser);
    }
}
