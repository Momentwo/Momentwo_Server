package cord.eoeo.momentwo.friendship.application.port.out.process;

import cord.eoeo.momentwo.friendship.adapter.dto.FriendshipAllListResponseDto;
import cord.eoeo.momentwo.user.domain.User;

public interface GetFriendshipPort {
    FriendshipAllListResponseDto getFriendship(User owner);
}
