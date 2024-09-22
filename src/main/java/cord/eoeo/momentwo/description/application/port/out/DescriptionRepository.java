package cord.eoeo.momentwo.description.application.port.out;

import cord.eoeo.momentwo.description.domain.Description;
import cord.eoeo.momentwo.photo.domain.Photo;

import java.util.Optional;

public interface DescriptionRepository {
    void save(Description description);
    void deleteByPhoto(Photo photo);
    Optional<Description> findByPhoto(Photo photo);
}
