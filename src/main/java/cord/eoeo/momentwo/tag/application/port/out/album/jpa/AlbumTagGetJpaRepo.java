package cord.eoeo.momentwo.tag.application.port.out.album.jpa;

import cord.eoeo.momentwo.tag.adapter.dto.out.album.AlbumTagQueryDto;
import cord.eoeo.momentwo.tag.application.port.out.album.AlbumTagGenericJpaRepo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlbumTagGetJpaRepo extends AlbumTagGenericJpaRepo {
    @Query("SELECT new cord.eoeo.momentwo.tag.adapter.dto.out.album.AlbumTagResponseDto(at.id, at.tag) FROM AlbumTag at WHERE at.album.id = :albumId")
    List<AlbumTagQueryDto> allTagByAlbumId(long albumId);
}
