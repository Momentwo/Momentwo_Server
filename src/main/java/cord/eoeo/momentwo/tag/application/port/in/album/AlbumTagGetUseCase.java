package cord.eoeo.momentwo.tag.application.port.in.album;

import cord.eoeo.momentwo.tag.adapter.dto.out.album.AlbumTagGetResponseDto;

public interface AlbumTagGetUseCase {
    AlbumTagGetResponseDto albumTagGet(long albumId);
}
