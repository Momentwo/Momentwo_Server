package cord.eoeo.momentwo.like.application.port.out;

import cord.eoeo.momentwo.like.domain.PhotoLike;
import cord.eoeo.momentwo.photo.domain.Photo;

import java.util.Optional;

public interface PhotoLikesRepository {
    void save(PhotoLike photoLike);
    Optional<PhotoLike> findByPhoto(Photo photo);
}
