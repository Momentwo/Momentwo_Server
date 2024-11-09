package cord.eoeo.momentwo.friendship.application.port.in;

import cord.eoeo.momentwo.friendship.adapter.dto.FriendshipSendListResponseDto;

public interface GetFriendshipSendUseCase {
    /**
     * 보낸 친구 요청 조회
     * @return : 보낸 친구 요청 목록
     */
    FriendshipSendListResponseDto getFriendshipSend();
}
