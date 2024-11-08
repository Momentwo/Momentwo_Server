package cord.eoeo.momentwo.friendship.adapter.out.process;

import cord.eoeo.momentwo.friendship.advice.exception.NotFoundFriendshipRequestException;
import cord.eoeo.momentwo.friendship.application.port.out.FriendshipGenericRepo;
import cord.eoeo.momentwo.friendship.application.port.out.delete.FriendshipDeleteFromAndToPort;
import cord.eoeo.momentwo.friendship.application.port.out.find.FriendshipFindToAndFromAndAcceptPort;
import cord.eoeo.momentwo.friendship.application.port.out.process.ResponseProcessPort;
import cord.eoeo.momentwo.friendship.domain.Friendship;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResponseProcessAdapter implements ResponseProcessPort {
    private final FriendshipFindToAndFromAndAcceptPort friendshipFindToAndFromAndAcceptPort;
    private final FriendshipDeleteFromAndToPort friendshipDeleteFromAndToPort;
    private final FriendshipGenericRepo friendshipGenericRepo;

    @Override
    public void responseProcess(User responseUser, User requestUser, Boolean accept) {
        Friendship friendship = friendshipFindToAndFromAndAcceptPort.findByToUserAndFromUserAndAccept(
                responseUser,
                requestUser,
                false
        ).orElseThrow(NotFoundFriendshipRequestException::new);

        if(!accept) {
            friendshipDeleteFromAndToPort.deleteByFromUserAndToUser(responseUser, requestUser);
            return;
        }

        friendship.setAccept(true);
        friendshipGenericRepo.save(friendship);
    }
}
