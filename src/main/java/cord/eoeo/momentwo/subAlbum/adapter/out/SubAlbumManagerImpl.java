package cord.eoeo.momentwo.subAlbum.adapter.out;

import cord.eoeo.momentwo.subAlbum.advice.exception.NotFoundSubAlbumException;
import cord.eoeo.momentwo.subAlbum.application.port.out.SubAlbumManager;
import cord.eoeo.momentwo.subAlbum.application.port.out.SubAlbumRepository;
import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubAlbumManagerImpl implements SubAlbumManager {
    private final SubAlbumRepository subAlbumRepository;

    @Override
    public SubAlbum getSubAlbumInfo(long subAlbum) {
        return subAlbumRepository.getSubAlbumInfo(subAlbum).orElseThrow(NotFoundSubAlbumException::new);
    }
}
