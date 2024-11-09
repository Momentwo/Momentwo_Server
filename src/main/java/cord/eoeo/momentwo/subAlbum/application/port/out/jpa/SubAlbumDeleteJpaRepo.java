package cord.eoeo.momentwo.subAlbum.application.port.out.jpa;

import cord.eoeo.momentwo.subAlbum.application.port.out.SubAlbumGenericJpaRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubAlbumDeleteJpaRepo extends SubAlbumGenericJpaRepo {
    @Modifying
    @Query("DELETE FROM SubAlbum s WHERE s.id IN :subAlbumIds")
    void deleteBySubAlbumIds(List<Long> subAlbumIds);
}
