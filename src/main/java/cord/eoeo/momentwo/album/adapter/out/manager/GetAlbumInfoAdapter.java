package cord.eoeo.momentwo.album.adapter.out.manager;

import cord.eoeo.momentwo.album.advice.exception.NotFoundAlbumException;
import cord.eoeo.momentwo.album.application.port.out.AlbumGenericRepo;
import cord.eoeo.momentwo.album.application.port.out.manager.GetAlbumInfoPort;
import cord.eoeo.momentwo.album.domain.Album;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GetAlbumInfoAdapter implements GetAlbumInfoPort {
    private final AlbumGenericRepo albumGenericRepo;

    @Override
    public Album getAlbumInfo(long id) {
        return albumGenericRepo.findById(id).orElseThrow(NotFoundAlbumException::new);
    }
}
