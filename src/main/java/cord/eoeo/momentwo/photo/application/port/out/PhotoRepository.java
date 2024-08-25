package cord.eoeo.momentwo.photo.application.port.out;

import cord.eoeo.momentwo.photo.domain.Photo;

import java.util.List;

public interface PhotoRepository {
    void save(Photo photo);

    List<Photo> findImageBySubAlbumId(long subAlbumId);

    void deleteAllBySubAlbumIdAndPhotoId(long subAlbumId, List<Long> imageIds);
}
