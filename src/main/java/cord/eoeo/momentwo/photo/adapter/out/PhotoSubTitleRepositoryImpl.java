package cord.eoeo.momentwo.photo.adapter.out;

import cord.eoeo.momentwo.photo.application.port.out.PhotoSubTitleRepository;
import cord.eoeo.momentwo.photo.application.port.out.PhotoSubtitleJpaRepository;
import cord.eoeo.momentwo.photo.domain.PhotoSubTitle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PhotoSubTitleRepositoryImpl implements PhotoSubTitleRepository {
    private final PhotoSubtitleJpaRepository photoSubtitleJpaRepository;

    @Override
    public void save(PhotoSubTitle photoSubTitle) {
        photoSubtitleJpaRepository.save(photoSubTitle);
    }

    @Override
    public Optional<PhotoSubTitle> findById(long photoSubTitleId) {
        return photoSubtitleJpaRepository.findById(photoSubTitleId);
    }

    @Override
    public List<PhotoSubTitle> findSubTitleBySubAlbumId(long subAlbumId) {
        return photoSubtitleJpaRepository.findSubTitleBySubAlbumId(subAlbumId);
    }
}
