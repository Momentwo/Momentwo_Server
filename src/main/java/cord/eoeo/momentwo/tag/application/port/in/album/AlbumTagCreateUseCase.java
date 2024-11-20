package cord.eoeo.momentwo.tag.application.port.in.album;

import cord.eoeo.momentwo.tag.adapter.dto.in.album.AlbumTagCreateRequestDto;

public interface AlbumTagCreateUseCase {
    void albumTagCreate(AlbumTagCreateRequestDto albumTagCreateRequestDto);
}
