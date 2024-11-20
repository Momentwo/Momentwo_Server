package cord.eoeo.momentwo.tag.application.port.in.album;

import cord.eoeo.momentwo.tag.adapter.dto.in.album.AlbumTagUpdateRequestDto;

public interface AlbumTagUpdateUseCase {
    void albumTagUpdate(AlbumTagUpdateRequestDto albumTagUpdateRequestDto);
}
