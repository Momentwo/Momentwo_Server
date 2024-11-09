package cord.eoeo.momentwo.description.application.port.out.delete;

import cord.eoeo.momentwo.photo.domain.Photo;

public interface DescriptionDeleteByPhotoRepo {
    void deleteByPhoto(Photo photo);
}
