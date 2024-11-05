package cord.eoeo.momentwo.subAlbum.application.port.in;

import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumListResponseDto;

public interface GetSubAlbumUseCase {
    /**
     * 앨범 조회
     * @param albumId : 앨범 아이디
     * @return : 서브 앨범 리스트 형식 반환
     */
    SubAlbumListResponseDto getSubAlbums(long albumId);
}
