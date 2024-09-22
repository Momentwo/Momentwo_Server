package cord.eoeo.momentwo.description.application.port.out;

import cord.eoeo.momentwo.description.domain.Description;
import cord.eoeo.momentwo.photo.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DescriptionJpaRepository extends JpaRepository<Description, Long> {
    @Query("DELETE FROM Description d WHERE d.photo = :photo")
    void deleteByPhoto(Photo photo);

    @Query("SELECT d FROM Description d WHERE d.photo = :photo")
    Optional<Description> findByPhoto(Photo photo);
}
