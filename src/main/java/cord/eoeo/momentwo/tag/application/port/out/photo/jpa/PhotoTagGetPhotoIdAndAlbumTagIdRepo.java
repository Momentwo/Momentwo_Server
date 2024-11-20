package cord.eoeo.momentwo.tag.application.port.out.photo.jpa;

import cord.eoeo.momentwo.tag.application.port.out.photo.PhotoTagGenericJpaRepo;
import cord.eoeo.momentwo.tag.domain.PhotoTag;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PhotoTagGetPhotoIdAndAlbumTagIdRepo extends PhotoTagGenericJpaRepo {
    @Query("SELECT pt FROM PhotoTag pt WHERE pt.photo.id = :photoId AND pt.albumTag.id = :albumTagId")
    Optional<PhotoTag> getPhotoIdAndAlbumTagId(long photoId, long albumTagId);
}
