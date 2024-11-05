package cord.eoeo.momentwo.subAlbum.application.port.in;

import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumDeleteRequestDto;

public interface DeleteSubAlbumUseCase {
    /**
     * 서브 앨범 삭제
     * @param subAlbumDeleteRequestDto
     * albumId : 앨범 아이디
     * subAlbumIds : 서브 앨범의 아이디 정보
     */
    void deleteSubAlbums(SubAlbumDeleteRequestDto subAlbumDeleteRequestDto);
}
