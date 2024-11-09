package cord.eoeo.momentwo.photo.application.port.out.delete;

import java.util.List;

public interface PhotoDeleteAllSubAlbumIdAndPhotoIdPort {
    void deleteAllBySubAlbumIdAndPhotoId(long subAlbumId, List<Long> imageIds);
}
