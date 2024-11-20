package cord.eoeo.momentwo.tag.adapter.out.album.manager;

import cord.eoeo.momentwo.tag.application.port.out.album.jpa.AlbumTagCountRepo;
import cord.eoeo.momentwo.tag.application.port.out.album.manager.AlbumTagCountPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlbumTagCountAdapter implements AlbumTagCountPort {
    private final AlbumTagCountRepo albumTagCountRepo;

    @Override
    public int albumTagCount(long albumId) {
        return albumTagCountRepo.albumTagCount(albumId);
    }
}
