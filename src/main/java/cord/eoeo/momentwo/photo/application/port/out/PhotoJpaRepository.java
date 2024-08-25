package cord.eoeo.momentwo.photo.application.port.out;

import cord.eoeo.momentwo.photo.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhotoJpaRepository extends JpaRepository<Photo, Long> {
    @Query("SELECT p FROM Photo p WHERE p.subAlbum.id = :subAlbumId")
    List<Photo> findImageBySubAlbumId(long subAlbumId);

    @Modifying
    @Query("DELETE FROM Photo p WHERE p.subAlbum.id = :subAlbumId AND p.id IN :imageIds")
    void deleteAllBySubAlbumIdAndPhotoId(long subAlbumId, List<Long> imageIds);
}