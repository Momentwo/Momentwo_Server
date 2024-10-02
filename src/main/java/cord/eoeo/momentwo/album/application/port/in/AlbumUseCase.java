package cord.eoeo.momentwo.album.application.port.in;

import cord.eoeo.momentwo.album.adapter.dto.*;

public interface AlbumUseCase {
    void createAlbums(AlbumCreateRequestDto albumCreateRequestDto);

    void editAlbumsTitle(long id, AlbumTitleEditRequestDto albumTitleEditRequestDto);

    void deleteAlbums(long id);

    AlbumInfoListResponseDto getAlbums();

    AlbumRulesResponseDto getAlbumsRules(AlbumRulesRequestDto albumRulesRequestDto);
}
