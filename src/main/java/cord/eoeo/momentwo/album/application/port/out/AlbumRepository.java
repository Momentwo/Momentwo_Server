package cord.eoeo.momentwo.album.application.port.out;

import cord.eoeo.momentwo.album.domain.Album;

import java.util.Optional;

public interface AlbumRepository {
    void save(Album album);

    Optional<Album> findById(long id);

    void deleteAlbum(long id);
}
