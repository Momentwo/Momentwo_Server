package cord.eoeo.momentwo.description.application.port.out.manager;

import cord.eoeo.momentwo.photo.domain.Photo;

public interface DescriptionGetPhotoPort {
    Photo getPhoto(long photoId);
}
