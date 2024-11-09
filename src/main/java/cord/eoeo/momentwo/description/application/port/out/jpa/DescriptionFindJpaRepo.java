package cord.eoeo.momentwo.description.application.port.out.jpa;

import cord.eoeo.momentwo.description.application.port.out.DescriptionGenericJpaRepo;
import cord.eoeo.momentwo.description.domain.Description;
import cord.eoeo.momentwo.photo.domain.Photo;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DescriptionFindJpaRepo extends DescriptionGenericJpaRepo {
    @Query("SELECT d FROM Description d WHERE d.photo = :photo")
    Optional<Description> findByPhoto(Photo photo);
}
