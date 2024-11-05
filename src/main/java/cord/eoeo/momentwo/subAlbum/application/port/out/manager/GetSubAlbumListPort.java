package cord.eoeo.momentwo.subAlbum.application.port.out.manager;

import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumListResponseDto;

public interface GetSubAlbumListPort {
    SubAlbumListResponseDto getSubAlbumList(long albumId);
}
