package cord.eoeo.momentwo.tag.application.port.out.photo.jpa;

import cord.eoeo.momentwo.tag.application.port.out.photo.PhotoTagGenericJpaRepo;
import org.springframework.data.jpa.repository.Query;

public interface PhotoTagCountRepo extends PhotoTagGenericJpaRepo {
    @Query("SELECT COUNT(pt) FROM PhotoTag pt WHERE pt.photo.id = :photoId")
    int photoTagCount(long photoId);
}
