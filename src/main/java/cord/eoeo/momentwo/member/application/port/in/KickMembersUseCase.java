package cord.eoeo.momentwo.member.application.port.in;

import java.util.List;

public interface KickMembersUseCase {
    /**
     * 멤버 추방
     * @param albumId
     * @param kickUsersId
     * albumId : 앨범 아이디
     * kickMemberList : 추방할 멤버
     */
    void kickMembers(Long albumId, List<Long> kickUsersId);
}
