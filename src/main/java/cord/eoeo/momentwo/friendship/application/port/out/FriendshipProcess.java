package cord.eoeo.momentwo.friendship.application.port.out;

import cord.eoeo.momentwo.friendship.adapter.dto.FriendshipAllListResponseDto;
import cord.eoeo.momentwo.friendship.adapter.dto.FriendshipReceiveListResponseDto;
import cord.eoeo.momentwo.friendship.adapter.dto.FriendshipSendListResponseDto;
import cord.eoeo.momentwo.user.domain.User;

public interface FriendshipProcess {
    void friendsRequest(User from, User to, boolean type);

    void responseProcess(User responseUser, User requestUser, Boolean accept);

    boolean isRequestFriends(User from, User to);

    void requestCancel(User from, User to);

    FriendshipAllListResponseDto getFriendship(User owner);

    FriendshipSendListResponseDto getFriendshipSend(User owner);

    FriendshipReceiveListResponseDto getFriendshipReceive(User owner);

    void deleteFriends(User toUser, User fromUser);
}
