package cord.eoeo.momentwo.photo.application.port.out.jpa;

import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.photo.application.port.out.PhotoGenericJpaRepo;
import org.springframework.data.jpa.repository.Query;

public interface PhotoGetJpaRepo extends PhotoGenericJpaRepo {
    @Query("SELECT COUNT(p) FROM Photo p WHERE p.album = :album")
    int getAlbumCount(Album album);
}
