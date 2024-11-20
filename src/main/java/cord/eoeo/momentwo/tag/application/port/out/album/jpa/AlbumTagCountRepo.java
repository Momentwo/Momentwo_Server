package cord.eoeo.momentwo.tag.application.port.out.album.jpa;

import cord.eoeo.momentwo.tag.application.port.out.album.AlbumTagGenericJpaRepo;
import org.springframework.data.jpa.repository.Query;

public interface AlbumTagCountRepo extends AlbumTagGenericJpaRepo {
    @Query("SELECT COUNT(at) FROM AlbumTag at WHERE at.album.id = :albumId")
    int albumTagCount(long albumId);
}
