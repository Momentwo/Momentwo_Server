package cord.eoeo.momentwo.elasticsearch.application.port.out.tag.manager;

import cord.eoeo.momentwo.album.domain.Album;

import java.util.List;

public interface AlbumTagsSavePort {
    void albumTagsSave(Album album, List<String> albumTags);
}
