package cord.eoeo.momentwo.friendship.adapter.out.process;

import cord.eoeo.momentwo.friendship.adapter.dto.FriendshipSendListResponseDto;
import cord.eoeo.momentwo.friendship.application.port.out.find.FriendshipFindSendFriendsPort;
import cord.eoeo.momentwo.friendship.application.port.out.process.GetFriendshipSendPort;
import cord.eoeo.momentwo.friendship.domain.Friendship;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetFriendshipSendAdapter implements GetFriendshipSendPort {
    private final FriendshipFindSendFriendsPort friendshipFindSendFriendsPort;

    @Override
    public FriendshipSendListResponseDto getFriendshipSend(User owner) {
        List<Friendship> friendships = friendshipFindSendFriendsPort.findSendFriendsByUser(owner);

        return new FriendshipSendListResponseDto().toDo(friendships);
    }
}
