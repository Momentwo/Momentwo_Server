package cord.eoeo.momentwo.friendship.application.port.in;

import cord.eoeo.momentwo.friendship.adapter.dto.RequestFriendshipDto;

public interface RequestFriendshipUseCase {
    /**
     * 친구 요청
     * @param requestFriendshipDto
     * nickname : 닉네임
     */
    void requestFriendship(RequestFriendshipDto requestFriendshipDto);
}
