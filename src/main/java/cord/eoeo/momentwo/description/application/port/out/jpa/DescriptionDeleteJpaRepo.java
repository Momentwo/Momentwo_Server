package cord.eoeo.momentwo.description.application.port.out.jpa;

import cord.eoeo.momentwo.description.application.port.out.DescriptionGenericJpaRepo;
import cord.eoeo.momentwo.photo.domain.Photo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DescriptionDeleteJpaRepo extends DescriptionGenericJpaRepo {
    @Modifying
    @Query("DELETE FROM Description d WHERE d.photo = :photo")
    void deleteByPhoto(Photo photo);
}
