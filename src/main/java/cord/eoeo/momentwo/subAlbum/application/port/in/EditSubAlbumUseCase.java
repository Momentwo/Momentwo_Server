package cord.eoeo.momentwo.subAlbum.application.port.in;

import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumEditTitleRequestDto;

public interface EditSubAlbumUseCase {
    /**
     * 서브 앨범 제목 수정
     * @param subAlbumEditTitleRequestDto
     * albumId : 앨범 아이디
     * subAlbumId : 서브 앨범 아이디
     * editTitle : 변경할 제목
     */
    void editSubAlbumTitle(SubAlbumEditTitleRequestDto subAlbumEditTitleRequestDto);
}
