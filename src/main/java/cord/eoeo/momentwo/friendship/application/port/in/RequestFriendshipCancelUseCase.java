package cord.eoeo.momentwo.friendship.application.port.in;

import cord.eoeo.momentwo.friendship.adapter.dto.RequestFriendshipDto;

public interface RequestFriendshipCancelUseCase {
    /**
     * 친구요청 취소
     * @param requestFriendshipDto
     * nickname : 닉네임
     */
    void requestFriendshipCancel(RequestFriendshipDto requestFriendshipDto);
}
