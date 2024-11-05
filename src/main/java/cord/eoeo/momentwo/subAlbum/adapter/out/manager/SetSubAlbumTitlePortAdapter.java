package cord.eoeo.momentwo.subAlbum.adapter.out.manager;

import cord.eoeo.momentwo.subAlbum.advice.exception.NotFoundSubAlbumException;
import cord.eoeo.momentwo.subAlbum.application.port.out.SubAlbumGenericRepo;
import cord.eoeo.momentwo.subAlbum.application.port.out.manager.SetSubAlbumTitlePort;
import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SetSubAlbumTitlePortAdapter implements SetSubAlbumTitlePort {
    private final SubAlbumGenericRepo subAlbumGenericRepo;

    @Override
    @Transactional
    public void setSubAlbumTitle(long subAlbumId, String editTitle) {
        SubAlbum subAlbum = subAlbumGenericRepo.findById(subAlbumId)
                .orElseThrow(NotFoundSubAlbumException::new);
        subAlbum.setSubAlbumTitle(editTitle);
        subAlbumGenericRepo.save(subAlbum);
    }
}
