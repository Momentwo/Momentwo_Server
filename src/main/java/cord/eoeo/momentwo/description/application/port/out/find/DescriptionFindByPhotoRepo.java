package cord.eoeo.momentwo.description.application.port.out.find;

import cord.eoeo.momentwo.description.domain.Description;
import cord.eoeo.momentwo.photo.domain.Photo;

import java.util.Optional;

public interface DescriptionFindByPhotoRepo {
    Optional<Description> findByPhoto(Photo photo);
}
