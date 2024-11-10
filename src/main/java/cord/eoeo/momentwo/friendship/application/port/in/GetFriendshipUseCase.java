package cord.eoeo.momentwo.friendship.application.port.in;

import cord.eoeo.momentwo.friendship.adapter.dto.FriendshipAllListResponseDto;

public interface GetFriendshipUseCase {
    /**
     * 친구목록 조회
     * @return : 친구목록
     */
    FriendshipAllListResponseDto getFriendship(int size, long cursor);
}
