package cord.eoeo.momentwo.friendship.application.port.out;

import cord.eoeo.momentwo.config.page.CursorPage;
import cord.eoeo.momentwo.friendship.domain.Friendship;
import cord.eoeo.momentwo.user.domain.User;
import org.springframework.data.domain.Pageable;

public interface FriendshipAllFriendsPageCustomPort {
    CursorPage<Friendship> allFriendsCustom(User owner, Pageable pageable, long cursorId);
}
