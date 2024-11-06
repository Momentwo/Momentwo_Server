package cord.eoeo.momentwo.album.application.port.in;

import cord.eoeo.momentwo.album.adapter.dto.AlbumInfoListResponseDto;

public interface GetAlbumUseCase {
    /**
     * 앨범 정보 조회
     * @return : 앨범 리스트 반환
     */
    AlbumInfoListResponseDto getAlbums();
}
