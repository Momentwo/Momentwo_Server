package cord.eoeo.momentwo.subAlbum.application.port.out.get;

import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;

import java.util.Optional;

public interface GetSubAlbumInfoRepo {
    Optional<SubAlbum> getSubAlbumInfo(long subAlbum);
}
