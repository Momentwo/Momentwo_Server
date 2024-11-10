package cord.eoeo.momentwo.friendship.adapter.out.process;

import cord.eoeo.momentwo.config.page.CursorPage;
import cord.eoeo.momentwo.friendship.adapter.dto.FriendshipAllListResponseDto;
import cord.eoeo.momentwo.friendship.application.port.out.FriendshipAllFriendsPageCustomPort;
import cord.eoeo.momentwo.friendship.application.port.out.find.FriendshipFindAllFriendsPort;
import cord.eoeo.momentwo.friendship.application.port.out.process.GetAllFriendship;
import cord.eoeo.momentwo.friendship.application.port.out.process.GetFriendshipPort;
import cord.eoeo.momentwo.friendship.domain.Friendship;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetFriendshipAdapter implements GetAllFriendship {
    private final FriendshipAllFriendsPageCustomPort friendshipAllFriendsPageCustomPort;

    @Override
    public FriendshipAllListResponseDto getAllFriendship(User owner, Pageable pageable, long cursorId) {
        CursorPage<Friendship> friendships = friendshipAllFriendsPageCustomPort
                .allFriendsCustom(owner, pageable, cursorId);

        return new FriendshipAllListResponseDto().toDo(friendships);
    }
}
