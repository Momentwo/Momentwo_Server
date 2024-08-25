package cord.eoeo.momentwo.subAlbum.application.port.out;

import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;

public interface SubAlbumManager {
    SubAlbum getSubAlbumInfo(long subAlbumId);
}
