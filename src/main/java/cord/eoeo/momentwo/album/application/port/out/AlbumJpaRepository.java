package cord.eoeo.momentwo.album.application.port.out;

import cord.eoeo.momentwo.album.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AlbumJpaRepository extends JpaRepository<Album, Long> {
    @Modifying
    @Query("DELETE FROM Album album WHERE album.id = :id")
    void deleteAlbum(long id);
}
