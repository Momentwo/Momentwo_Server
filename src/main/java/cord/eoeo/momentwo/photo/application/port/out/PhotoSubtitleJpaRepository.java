package cord.eoeo.momentwo.photo.application.port.out;

import cord.eoeo.momentwo.photo.domain.PhotoSubTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoSubtitleJpaRepository extends JpaRepository<PhotoSubTitle, Long> {
    @Query("SELECT pst FROM PhotoSubTitle pst WHERE pst.subAlbum.id = :subAlbumId")
    List<PhotoSubTitle> findSubTitleBySubAlbumId(long subAlbumId);
}
