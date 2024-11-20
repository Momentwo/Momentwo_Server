package cord.eoeo.momentwo.tag.application.port.out.photo.jpa;

import cord.eoeo.momentwo.tag.application.port.out.photo.PhotoTagGenericJpaRepo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhotoTagGetJpaRepo extends PhotoTagGenericJpaRepo {
    @Query("SELECT pt.albumTag.tag FROM PhotoTag pt WHERE pt.photo.id = :photoId")
    List<String> photoAllTags(long photoId);
}
