package cord.eoeo.momentwo.subAlbum.application.port.out.manager;

import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;

public interface GetSubAlbumInfoPort {
    SubAlbum getSubAlbumInfo(long subAlbumId);
}
