package cord.eoeo.momentwo.friendship.adapter.out.process;

import cord.eoeo.momentwo.friendship.adapter.dto.FriendshipReceiveListResponseDto;
import cord.eoeo.momentwo.friendship.application.port.out.find.FriendshipFindReceiveFriendsPort;
import cord.eoeo.momentwo.friendship.application.port.out.process.GetFriendshipReceivePort;
import cord.eoeo.momentwo.friendship.domain.Friendship;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetFriendshipReceiveAdapter implements GetFriendshipReceivePort {
    private final FriendshipFindReceiveFriendsPort friendshipFindReceiveFriendsPort;

    @Override
    public FriendshipReceiveListResponseDto getFriendshipReceive(User owner) {
        List<Friendship> friendships = friendshipFindReceiveFriendsPort.findReceiveFriendsByUser(owner);

        return new FriendshipReceiveListResponseDto().toDO(friendships);
    }
}
