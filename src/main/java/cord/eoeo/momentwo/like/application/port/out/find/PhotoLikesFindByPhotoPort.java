package cord.eoeo.momentwo.like.application.port.out.find;

import cord.eoeo.momentwo.like.domain.PhotoLike;
import cord.eoeo.momentwo.photo.domain.Photo;

import java.util.Optional;

public interface PhotoLikesFindByPhotoPort {
    Optional<PhotoLike> findByPhoto(Photo photo);
}
