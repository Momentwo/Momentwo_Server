package cord.eoeo.momentwo.member.application.port.in;

import cord.eoeo.momentwo.member.adapter.out.dto.AlbumMemberResponseDto;

public interface GetMembersUseCase {
    /**
     * 멤버 조회
     * @param albumId : 앨범 아이디
     * @return : 멤버 리턴
     */
    AlbumMemberResponseDto getMembers(long albumId);
}
