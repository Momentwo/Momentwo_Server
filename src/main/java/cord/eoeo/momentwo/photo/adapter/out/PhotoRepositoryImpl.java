package cord.eoeo.momentwo.photo.adapter.out;

import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.photo.application.port.out.PhotoJpaRepository;
import cord.eoeo.momentwo.photo.application.port.out.PhotoRepository;
import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PhotoRepositoryImpl implements PhotoRepository {
    private final PhotoJpaRepository photoJpaRepository;

    @Override
    public void save(Photo photo) {
        photoJpaRepository.save(photo);
    }

    @Override
    public void deleteAllBySubAlbumIdAndPhotoId(long subAlbumId, List<Long> imageIds) {
        photoJpaRepository.deleteAllBySubAlbumIdAndPhotoId(subAlbumId, imageIds);
    }

    @Override
    public int getAlbumCount(Album album) {
        return photoJpaRepository.getAlbumCount(album);
    }

    @Override
    public Optional<Photo> findById(long id) {
        return photoJpaRepository.findById(id);
    }

    @Override
    public Optional<Photo> findByIdAndUser(long id, User user) {
        return photoJpaRepository.findByIdAndUser(id, user);
    }
}
