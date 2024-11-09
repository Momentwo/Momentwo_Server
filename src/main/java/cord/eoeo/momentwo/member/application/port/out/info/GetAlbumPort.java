package cord.eoeo.momentwo.member.application.port.out.info;

import cord.eoeo.momentwo.album.domain.Album;

import java.util.concurrent.CompletableFuture;

public interface GetAlbumPort {
    CompletableFuture<Album> getAlbum(long id);
}
