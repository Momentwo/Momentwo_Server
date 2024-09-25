package cord.eoeo.momentwo.like.adapter.out;

import cord.eoeo.momentwo.like.application.port.out.PhotoLikesJpaRepository;
import cord.eoeo.momentwo.like.application.port.out.PhotoLikesRepository;
import cord.eoeo.momentwo.like.domain.PhotoLike;
import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PhotoLikesRepositoryImpl implements PhotoLikesRepository {
    private final PhotoLikesJpaRepository photoLikesJpaRepository;

    @Override
    public void save(PhotoLike photoLike) {
        photoLikesJpaRepository.save(photoLike);
    }

    @Override
    public Optional<PhotoLike> findByPhoto(Photo photo) {
        return photoLikesJpaRepository.findByPhoto(photo);
    }
}
