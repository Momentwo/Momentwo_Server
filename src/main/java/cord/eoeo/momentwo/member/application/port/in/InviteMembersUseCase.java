package cord.eoeo.momentwo.member.application.port.in;

import cord.eoeo.momentwo.member.adapter.in.dto.InviteMembersRequestDto;

public interface InviteMembersUseCase {
    /**
     * 앨범 멤버 초대
     * @param inviteMembersRequestDto
     * albumId : 앨범 아이디
     * inviteNicknames : 초대할 멤버 닉네임
     */
    void inviteMembers(InviteMembersRequestDto inviteMembersRequestDto);
}
