package cord.eoeo.momentwo.photo.application.port.out;

import cord.eoeo.momentwo.photo.domain.Photo;

public interface PhotoRepository {
    void save(Photo photo);
}
