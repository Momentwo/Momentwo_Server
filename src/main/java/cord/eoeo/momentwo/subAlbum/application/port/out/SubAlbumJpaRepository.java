package cord.eoeo.momentwo.subAlbum.application.port.out;

import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubAlbumJpaRepository extends JpaRepository<SubAlbum, Long> {
    @Query("SELECT s FROM SubAlbum s WHERE s.album.id = :albumId")
    List<SubAlbum> getSubAlbumListByAlbumId(long albumId);

    @Modifying
    @Query("DELETE FROM SubAlbum s WHERE s.id IN :subAlbumIds")
    void deleteBySubAlbumIds(List<Long> subAlbumIds);
}
