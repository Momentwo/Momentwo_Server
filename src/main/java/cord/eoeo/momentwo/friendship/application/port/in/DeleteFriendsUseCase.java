package cord.eoeo.momentwo.friendship.application.port.in;

import cord.eoeo.momentwo.friendship.adapter.dto.RequestFriendshipDto;

public interface DeleteFriendsUseCase {
    /**
     * 친구 삭제
     * @param requestFriendshipDto
     * nickname : 닉네임
     */
    void deleteFriends(RequestFriendshipDto requestFriendshipDto);
}
