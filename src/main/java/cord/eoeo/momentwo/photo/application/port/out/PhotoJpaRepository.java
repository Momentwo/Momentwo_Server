package cord.eoeo.momentwo.photo.application.port.out;

import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PhotoJpaRepository extends JpaRepository<Photo, Long> {
    @Modifying
    @Query("DELETE FROM Photo p WHERE p.subAlbum.id = :subAlbumId AND p.id IN :imageIds")
    void deleteAllBySubAlbumIdAndPhotoId(long subAlbumId, List<Long> imageIds);

    @Query("SELECT COUNT(p) FROM Photo p WHERE p.album = :album")
    int getAlbumCount(Album album);

    @Query("SELECT p FROM Photo p WHERE p.id = :id AND p.user = :user")
    Optional<Photo> findByIdAndUser(long id, User user);
}
