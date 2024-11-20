package cord.eoeo.momentwo.tag.application.port.out.album.manager;

import java.util.List;

public interface AlbumTagGetAllTagPort {
    List<String> allTagByAlbumId(long albumId);
}
