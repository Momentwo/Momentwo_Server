package cord.eoeo.momentwo.photo.application.port.out.get;

import cord.eoeo.momentwo.album.domain.Album;

public interface PhotoGetAlbumCountPort {
    int getAlbumCount(Album album);
}
