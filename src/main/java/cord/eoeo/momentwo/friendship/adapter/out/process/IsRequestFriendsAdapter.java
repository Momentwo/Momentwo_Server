package cord.eoeo.momentwo.friendship.adapter.out.process;

import cord.eoeo.momentwo.friendship.advice.exception.AlreadyFriendshipException;
import cord.eoeo.momentwo.friendship.application.port.out.find.FriendshipFindToAndFromPort;
import cord.eoeo.momentwo.friendship.application.port.out.process.IsRequestFriendsPort;
import cord.eoeo.momentwo.friendship.domain.Friendship;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IsRequestFriendsAdapter implements IsRequestFriendsPort {
    private final FriendshipFindToAndFromPort friendshipFindToAndFromPort;

    @Override
    public boolean isRequestFriends(User fromUser, User toUser) {
        // 요청 받은 사람의 accept 이 false 인지 확인
        Friendship friendship = friendshipFindToAndFromPort.findByToUserAndFromUser(toUser, fromUser);

        if(friendship == null) {
            return true;
        }

        if(friendship.isAccept()) {
            throw new AlreadyFriendshipException();
        }
        return false;
    }
}
