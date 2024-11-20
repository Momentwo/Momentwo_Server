package cord.eoeo.momentwo.album.application.port.in;

import cord.eoeo.momentwo.album.adapter.dto.out.AlbumRulesResponseDto;

public interface GetAlbumRulesUseCase {
    /**
     * 앨범 권한 반환
     * @param albumId : 앨범 아이디
     * @return : 앨범 권한 반환
     */
    AlbumRulesResponseDto getAlbumsRules(long albumId);
}
