package cord.eoeo.momentwo.friendship.adapter.out.process;

import cord.eoeo.momentwo.friendship.adapter.dto.FriendshipAllListResponseDto;
import cord.eoeo.momentwo.friendship.application.port.out.find.FriendshipFindAllFriendsPort;
import cord.eoeo.momentwo.friendship.application.port.out.process.GetFriendshipPort;
import cord.eoeo.momentwo.friendship.domain.Friendship;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetFriendshipAdapter implements GetFriendshipPort {
    private final FriendshipFindAllFriendsPort friendshipFindAllFriendsPort;

    @Override
    public FriendshipAllListResponseDto getFriendship(User owner) {
        List<Friendship> friendships = friendshipFindAllFriendsPort.findAllFriendsByUser(owner);

        return new FriendshipAllListResponseDto().toDo(friendships);
    }
}
