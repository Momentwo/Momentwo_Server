package cord.eoeo.momentwo.photo.adapter.out.get;

import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.photo.application.port.out.get.PhotoGetAlbumCountPort;
import cord.eoeo.momentwo.photo.application.port.out.jpa.PhotoGetJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PhotoGetAlbumCountAdapter implements PhotoGetAlbumCountPort {
    private final PhotoGetJpaRepo photoGetJpaRepo;

    @Override
    public int getAlbumCount(Album album) {
        return photoGetJpaRepo.getAlbumCount(album);
    }
}
