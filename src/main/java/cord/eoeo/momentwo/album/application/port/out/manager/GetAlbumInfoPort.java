package cord.eoeo.momentwo.album.application.port.out.manager;

import cord.eoeo.momentwo.album.domain.Album;

public interface GetAlbumInfoPort {
    Album getAlbumInfo(long id);
}
