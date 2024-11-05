package cord.eoeo.momentwo.subAlbum.application.port.in;

import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumCreateRequestDto;

public interface CreateSubAlbumUseCase {
    /**
     * 서브앨범 생성
     * @param subAlbumCreateRequestDto
     * albumId : 앨범 아이디
     * title : 제목
     */
    void createSubAlbum(SubAlbumCreateRequestDto subAlbumCreateRequestDto);
}
