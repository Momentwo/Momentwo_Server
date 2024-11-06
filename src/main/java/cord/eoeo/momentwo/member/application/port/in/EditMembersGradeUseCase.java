package cord.eoeo.momentwo.member.application.port.in;

import cord.eoeo.momentwo.member.adapter.in.dto.EditGradeListRequestDto;

public interface EditMembersGradeUseCase {
    /**
     * 멤버 권한 변경 (앨범 수정 권한)
     * @param editGradeListRequestDto
     * albumId : 앨범 아이디
     * editMemberList : 권한을 변경할 유저 (닉네임 : 권한)
     */
    // 멤버 권한 변경 (앨범 수정 권한)
    void editMembersGrade(EditGradeListRequestDto editGradeListRequestDto);
}
