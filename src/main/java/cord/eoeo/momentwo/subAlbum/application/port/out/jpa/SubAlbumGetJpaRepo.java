package cord.eoeo.momentwo.subAlbum.application.port.out.jpa;

import cord.eoeo.momentwo.subAlbum.application.port.out.SubAlbumGenericJpaRepo;
import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubAlbumGetJpaRepo extends SubAlbumGenericJpaRepo {
    @Query("SELECT s FROM SubAlbum s WHERE s.album.id = :albumId")
    List<SubAlbum> getSubAlbumListByAlbumId(long albumId);
}
