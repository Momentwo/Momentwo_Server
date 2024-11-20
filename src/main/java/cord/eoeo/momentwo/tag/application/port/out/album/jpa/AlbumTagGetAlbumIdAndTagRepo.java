package cord.eoeo.momentwo.tag.application.port.out.album.jpa;

import cord.eoeo.momentwo.tag.application.port.out.album.AlbumTagGenericJpaRepo;
import cord.eoeo.momentwo.tag.domain.AlbumTag;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AlbumTagGetAlbumIdAndTagRepo extends AlbumTagGenericJpaRepo {
    @Query("SELECT at FROM AlbumTag at WHERE at.album.id = :albumId AND at.tag = :tag")
    Optional<AlbumTag> getAlbumIdAndTag(long albumId, String tag);
}
