package cord.eoeo.momentwo.photo.application.port.out;

import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface PhotoRepository {
    void save(Photo photo);

    void deleteAllBySubAlbumIdAndPhotoId(long subAlbumId, List<Long> imageIds);

    int getAlbumCount(Album album);

    Optional<Photo> findById(long id);

    Optional<Photo> findByIdAndUser(long id, User user);
}
