package cord.eoeo.momentwo.like.application.port.out;

import cord.eoeo.momentwo.like.domain.PhotoLike;
import cord.eoeo.momentwo.photo.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PhotoLikesJpaRepository extends JpaRepository<PhotoLike, Long> {
    @Query("SELECT pl FROM PhotoLike pl WHERE pl.photo = :photo")
    Optional<PhotoLike> findByPhoto(Photo photo);
}
