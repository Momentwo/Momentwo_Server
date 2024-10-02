package cord.eoeo.momentwo.album.application.port.in;

import cord.eoeo.momentwo.album.adapter.dto.*;

public interface AlbumUseCase {
    void createAlbums(AlbumCreateRequestDto albumCreateRequestDto);

    void editAlbumsTitle(AlbumTitleEditRequestDto albumTitleEditRequestDto);

    void deleteAlbums(AlbumDeleteRequestDto albumDeleteRequestDto);

    AlbumInfoListResponseDto getAlbums();

    AlbumRulesResponseDto getAlbumsRules(long albumId);
}
