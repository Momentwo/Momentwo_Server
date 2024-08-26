package cord.eoeo.momentwo.subAlbum.application.port.in;

import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumCreateRequestDto;
import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumDeleteRequestDto;
import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumEditTitleRequestDto;
import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumListResponseDto;

public interface SubAlbumUseCase {
    void createSubAlbum(SubAlbumCreateRequestDto subAlbumCreateRequestDto);
    SubAlbumListResponseDto getSubAlbums(long albumId);
    void editSubAlbumTitle(SubAlbumEditTitleRequestDto subAlbumEditTitleRequestDto);
    void deleteSubAlbums(SubAlbumDeleteRequestDto subAlbumDeleteRequestDto);
}
