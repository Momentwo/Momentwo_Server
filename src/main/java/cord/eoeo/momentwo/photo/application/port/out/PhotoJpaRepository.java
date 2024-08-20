package cord.eoeo.momentwo.photo.application.port.out;

import cord.eoeo.momentwo.photo.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoJpaRepository extends JpaRepository<Photo, Long> {
}
