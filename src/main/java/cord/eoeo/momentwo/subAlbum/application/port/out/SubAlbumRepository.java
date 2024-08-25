package cord.eoeo.momentwo.subAlbum.application.port.out;

import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;

import java.util.Optional;

public interface SubAlbumRepository {
    void save(SubAlbum subAlbum);

    Optional<SubAlbum> getSubAlbumInfo(long subAlbum);
}
