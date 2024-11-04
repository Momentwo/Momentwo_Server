package cord.eoeo.momentwo.photo.adapter.out.delete;

import cord.eoeo.momentwo.photo.application.port.out.delete.PhotoDeleteAllSubAlbumIdAndPhotoIdPort;
import cord.eoeo.momentwo.photo.application.port.out.jpa.PhotoDeleteJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PhotoDeleteAllSubAlbumIdAndPhotoIdAdapter implements PhotoDeleteAllSubAlbumIdAndPhotoIdPort {
    private final PhotoDeleteJpaRepo photoDeleteJpaRepo;
    @Override
    public void deleteAllBySubAlbumIdAndPhotoId(long subAlbumId, List<Long> imageIds) {
        photoDeleteJpaRepo.deleteAllBySubAlbumIdAndPhotoId(subAlbumId, imageIds);
    }
}
