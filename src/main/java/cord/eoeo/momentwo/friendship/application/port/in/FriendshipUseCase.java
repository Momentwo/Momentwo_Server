package cord.eoeo.momentwo.friendship.application.port.in;

import cord.eoeo.momentwo.friendship.adapter.in.dto.RequestFriendshipDto;
import cord.eoeo.momentwo.friendship.adapter.in.dto.ResponseFriendshipDto;

public interface FriendshipUseCase {
    void requestFriendship(RequestFriendshipDto requestFriendshipDto);

    void responseFriendship(ResponseFriendshipDto responseFriendshipDto);

    void requestFriendshipCancel(RequestFriendshipDto requestFriendshipDto);
}
