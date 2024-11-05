package cord.eoeo.momentwo.subAlbum.adapter.out.manager;

import cord.eoeo.momentwo.subAlbum.advice.exception.NotFoundSubAlbumException;
import cord.eoeo.momentwo.subAlbum.application.port.out.get.GetSubAlbumInfoRepo;
import cord.eoeo.momentwo.subAlbum.application.port.out.manager.GetSubAlbumInfoPort;
import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GetSubAlbumInfoPortAdapter implements GetSubAlbumInfoPort {
    private final GetSubAlbumInfoRepo getSubAlbumInfoRepo;

    @Override
    @Transactional(readOnly = true)
    public SubAlbum getSubAlbumInfo(long subAlbum) {
        return getSubAlbumInfoRepo.getSubAlbumInfo(subAlbum).orElseThrow(NotFoundSubAlbumException::new);
    }
}
