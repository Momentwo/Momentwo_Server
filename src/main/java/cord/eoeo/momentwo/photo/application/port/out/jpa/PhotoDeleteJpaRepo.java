package cord.eoeo.momentwo.photo.application.port.out.jpa;

import cord.eoeo.momentwo.photo.application.port.out.PhotoGenericJpaRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhotoDeleteJpaRepo extends PhotoGenericJpaRepo {
    @Modifying
    @Query("DELETE FROM Photo p WHERE p.subAlbum.id = :subAlbumId AND p.id IN :imageIds")
    void deleteAllBySubAlbumIdAndPhotoId(long subAlbumId, List<Long> imageIds);
}
