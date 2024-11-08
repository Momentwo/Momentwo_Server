package cord.eoeo.momentwo.like.application.port.out.jpa;

import cord.eoeo.momentwo.like.application.port.out.PhotoLikesGenericJpaRepo;
import cord.eoeo.momentwo.like.domain.PhotoLike;
import cord.eoeo.momentwo.photo.domain.Photo;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PhotoLikesFindJpaRepo extends PhotoLikesGenericJpaRepo {
    @Query("SELECT pl FROM PhotoLike pl WHERE pl.photo = :photo")
    Optional<PhotoLike> findByPhoto(Photo photo);
}
