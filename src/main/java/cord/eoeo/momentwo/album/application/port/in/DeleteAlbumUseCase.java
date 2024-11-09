package cord.eoeo.momentwo.album.application.port.in;

import cord.eoeo.momentwo.album.adapter.dto.AlbumDeleteRequestDto;

public interface DeleteAlbumUseCase {
    /**
     * 앨범 삭제
     * @param albumDeleteRequestDto
     * albumId : 앨범 아이디
     */
    void deleteAlbums(AlbumDeleteRequestDto albumDeleteRequestDto);
}
