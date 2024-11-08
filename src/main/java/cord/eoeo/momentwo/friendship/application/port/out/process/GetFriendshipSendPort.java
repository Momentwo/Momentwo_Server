package cord.eoeo.momentwo.friendship.application.port.out.process;

import cord.eoeo.momentwo.friendship.adapter.dto.FriendshipSendListResponseDto;
import cord.eoeo.momentwo.user.domain.User;

public interface GetFriendshipSendPort {
    FriendshipSendListResponseDto getFriendshipSend(User owner);
}
