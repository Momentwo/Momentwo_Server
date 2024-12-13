package cord.eoeo.momentwo.tag.application.port.out.album.manager;

import cord.eoeo.momentwo.tag.adapter.dto.out.album.AlbumTagQueryDto;

import java.util.List;

public interface AlbumTagGetAllTagPort {
    List<AlbumTagQueryDto> allTagByAlbumId(long albumId);
}
