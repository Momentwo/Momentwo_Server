package cord.eoeo.momentwo.photo.application.port.out;

import cord.eoeo.momentwo.photo.domain.Photo;

import java.util.List;

public interface PhotoRepository {
    void save(Photo photo);

    List<Photo> findImageByAlbumId(long albumId);
}
