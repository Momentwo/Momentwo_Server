package cord.eoeo.momentwo.tag.adapter.out.album.manager;

import cord.eoeo.momentwo.tag.adapter.dto.out.album.AlbumTagQueryDto;
import cord.eoeo.momentwo.tag.application.port.out.album.jpa.AlbumTagGetJpaRepo;
import cord.eoeo.momentwo.tag.application.port.out.album.manager.AlbumTagGetAllTagPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AlbumTagGetAllTagAdapter implements AlbumTagGetAllTagPort {
    private final AlbumTagGetJpaRepo albumTagGetJpaRepo;

    @Override
    public List<AlbumTagQueryDto> allTagByAlbumId(long albumId) {
        return albumTagGetJpaRepo.allTagByAlbumId(albumId);
    }
}
