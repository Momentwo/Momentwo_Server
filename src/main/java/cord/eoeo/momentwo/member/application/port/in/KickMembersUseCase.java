package cord.eoeo.momentwo.member.application.port.in;

import cord.eoeo.momentwo.member.adapter.in.dto.KickMembersRequestDto;

public interface KickMembersUseCase {
    /**
     * 멤버 추방
     * @param kickMembersRequestDto
     * albumId : 앨범 아이디
     * kickMemberList : 추방할 멤버
     */
    void kickMembers(KickMembersRequestDto kickMembersRequestDto);
}
