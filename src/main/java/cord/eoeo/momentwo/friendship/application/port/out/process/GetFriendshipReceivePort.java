package cord.eoeo.momentwo.friendship.application.port.out.process;

import cord.eoeo.momentwo.friendship.adapter.dto.FriendshipReceiveListResponseDto;
import cord.eoeo.momentwo.user.domain.User;

public interface GetFriendshipReceivePort {
    FriendshipReceiveListResponseDto getFriendshipReceive(User owner);
}
