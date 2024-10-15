package cord.eoeo.momentwo.member.application.port.in;

import cord.eoeo.momentwo.member.adapter.in.dto.*;
import cord.eoeo.momentwo.member.adapter.out.dto.AlbumMemberResponseDto;

public interface AlbumMemberUseCase {
    void inviteMembers(InviteMembersRequestDto inviteMembersRequestDto);

    // 멤버 조회
    AlbumMemberResponseDto getMembers(long albumId);

    // 멤버 추방
    void kickMembers(KickMembersRequestDto kickMembersRequestDto);

    // 멤버 권한 변경 (앨범 수정 권한)
    void editMembersGrade(EditGradeListRequestDto editGradeListRequestDto);

    // 관리자 권한 넘기기
    void assignAdmin(AssignAdminRequestDto assignAdminRequestDto);

    // 멤버 나가기 (개인)
    void outAlbum(MemberOutRequestDto memberOutRequestDto);
}
