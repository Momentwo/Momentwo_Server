package cord.eoeo.momentwo.tag.adapter.out.album.manager;

import cord.eoeo.momentwo.tag.application.port.out.album.AlbumTagGenericRepo;
import cord.eoeo.momentwo.tag.application.port.out.album.manager.AlbumTagDeletePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlbumTagDeleteAdapter implements AlbumTagDeletePort {
    private final AlbumTagGenericRepo albumTagGenericRepo;

    @Override
    public void albumTagDelete(long albumTagId) {
        albumTagGenericRepo.deleteById(albumTagId);
    }
}
