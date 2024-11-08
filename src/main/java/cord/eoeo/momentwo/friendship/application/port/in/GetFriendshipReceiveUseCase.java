package cord.eoeo.momentwo.friendship.application.port.in;

import cord.eoeo.momentwo.friendship.adapter.dto.FriendshipReceiveListResponseDto;

public interface GetFriendshipReceiveUseCase {
    /**
     * 받은 친구 요청 조회
     * @return : 받은 친구 요청 목록
     */
    FriendshipReceiveListResponseDto getFriendshipReceive();
}
