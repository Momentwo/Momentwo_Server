package cord.eoeo.momentwo.member.application.port.in;

import cord.eoeo.momentwo.member.adapter.in.dto.MemberOutRequestDto;

public interface OutAlbumUseCase {
    /**
     * 앨범 멤버 나가기
     * @param memberOutRequestDto
     * albumId : 앨범 아이디
     */
    void outAlbum(MemberOutRequestDto memberOutRequestDto);
}
