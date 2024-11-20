package cord.eoeo.momentwo.tag.application.port.in.album;

import cord.eoeo.momentwo.tag.adapter.dto.in.album.AlbumTagDeleteRequestDto;

public interface AlbumTagDeleteUseCase {
    void albumTagDelete(AlbumTagDeleteRequestDto albumTagDeleteRequestDto);
}
