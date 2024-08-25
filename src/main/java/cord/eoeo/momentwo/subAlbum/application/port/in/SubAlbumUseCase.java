package cord.eoeo.momentwo.subAlbum.application.port.in;

import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumCreateRequestDto;

public interface SubAlbumUseCase {
    void createSubAlbum(SubAlbumCreateRequestDto subAlbumCreateRequestDto);
}
