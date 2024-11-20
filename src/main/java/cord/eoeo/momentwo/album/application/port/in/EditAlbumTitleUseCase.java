package cord.eoeo.momentwo.album.application.port.in;

import cord.eoeo.momentwo.album.adapter.dto.in.AlbumTitleEditRequestDto;

public interface EditAlbumTitleUseCase {
    /**
     * 앨범 제목 수정
     * @param albumTitleEditRequestDto
     * albumId : 앨범 아이디
     * editTitle : 수정할 제목
     */
    void editAlbumsTitle(AlbumTitleEditRequestDto albumTitleEditRequestDto);
}
