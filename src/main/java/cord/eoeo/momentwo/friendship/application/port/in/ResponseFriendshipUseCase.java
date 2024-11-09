package cord.eoeo.momentwo.friendship.application.port.in;

import cord.eoeo.momentwo.friendship.adapter.dto.ResponseFriendshipDto;

public interface ResponseFriendshipUseCase {
    /**
     * 요청 수락 및 거절
     * @param responseFriendshipDto
     * nickname : 닉네임
     * accept : 수락 여부
     */
    void responseFriendship(ResponseFriendshipDto responseFriendshipDto);
}
