package cord.eoeo.momentwo.tag.application.port.out.album.jpa;

import cord.eoeo.momentwo.tag.application.port.out.album.AlbumTagGenericJpaRepo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlbumTagGetJpaRepo extends AlbumTagGenericJpaRepo {
    @Query("SELECT at.tag FROM AlbumTag at WHERE at.album.id = :albumId")
    List<String> allTagByAlbumId(long albumId);
}
