package cord.eoeo.momentwo.member.application.port.in;

import cord.eoeo.momentwo.member.adapter.in.dto.AssignAdminRequestDto;
import cord.eoeo.momentwo.member.adapter.in.dto.EditGradeListRequestDto;
import cord.eoeo.momentwo.member.adapter.in.dto.InviteMembersRequestDto;
import cord.eoeo.momentwo.member.adapter.in.dto.KickMembersRequestDto;
import cord.eoeo.momentwo.member.adapter.out.dto.AlbumMemberResponseDto;

public interface AlbumMemberUseCase {
    void inviteMembers(long albumId, InviteMembersRequestDto inviteMembersRequestDto);

    // 멤버 조회
    AlbumMemberResponseDto getMembers(long albumId);

    // 멤버 추방
    void kickMembers(long albumId, KickMembersRequestDto kickMembersRequestDto);

    // 멤버 권한 변경 (앨범 수정 권한)
    void editMembersGrade(long albumId, EditGradeListRequestDto editGradeListRequestDto);

    // 관리자 권한 넘기기
    void assignAdmin(long albumId, AssignAdminRequestDto assignAdminRequestDto);

    // 멤버 나가기 (개인)
    void outAlbum(long albumId);
}
