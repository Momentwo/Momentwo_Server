package cord.eoeo.momentwo.member.application.port.in;

import cord.eoeo.momentwo.member.adapter.in.dto.AssignAdminRequestDto;

public interface AssignAdminUseCase {
    /**
     * 관리자 권한 넘기기
     * @param assignAdminRequestDto
     * albumId : 앨범 아이디
     * nickname : 닉네임
     */
    void assignAdmin(AssignAdminRequestDto assignAdminRequestDto);
}
