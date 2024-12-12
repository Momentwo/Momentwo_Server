package cord.eoeo.momentwo.friendship.application.port.in;

public interface DeleteFriendsUseCase {
    /**
     * 친구 삭제
     * @param userId
     * userId : 유저 아이디
     */
    void deleteFriends(Long userId);
}
