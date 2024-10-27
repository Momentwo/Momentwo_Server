package cord.eoeo.momentwo.friendship.application.port.in;

import cord.eoeo.momentwo.friendship.adapter.dto.*;

public interface FriendshipUseCase {
    void requestFriendship(RequestFriendshipDto requestFriendshipDto);

    void responseFriendship(ResponseFriendshipDto responseFriendshipDto);

    void requestFriendshipCancel(RequestFriendshipDto requestFriendshipDto);

    FriendshipAllListResponseDto getFriendship();

    FriendshipSendListResponseDto getFriendshipSend();

    FriendshipReceiveListResponseDto getFriendshipReceive();

    void deleteFriends(RequestFriendshipDto requestFriendshipDto);
}
