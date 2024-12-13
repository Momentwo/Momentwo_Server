package cord.eoeo.momentwo.tag.application.port.in.album;

import cord.eoeo.momentwo.tag.adapter.dto.out.album.AlbumTagListResponseDto;

public interface AlbumTagGetUseCase {
    AlbumTagListResponseDto albumTagGet(long albumId);
}
