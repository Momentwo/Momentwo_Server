package cord.eoeo.momentwo.friendship.application.port.in;

public interface RequestFriendshipCancelUseCase {
    /**
     * 친구요청 취소
     * @param userId
     * userId : 유저 아이디
     */
    void requestFriendshipCancel(Long userId);
}
