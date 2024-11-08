package cord.eoeo.momentwo.like.adapter.out.find;

import cord.eoeo.momentwo.like.application.port.out.find.PhotoLikesFindByPhotoPort;
import cord.eoeo.momentwo.like.application.port.out.jpa.PhotoLikesFindJpaRepo;
import cord.eoeo.momentwo.like.domain.PhotoLike;
import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PhotoLikesFindByPhotoAdapter implements PhotoLikesFindByPhotoPort {
    private final PhotoLikesFindJpaRepo photoLikesFindJpaRepo;

    @Override
    public Optional<PhotoLike> findByPhoto(Photo photo) {
        return photoLikesFindJpaRepo.findByPhoto(photo);
    }
}
