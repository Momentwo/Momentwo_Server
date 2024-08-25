package cord.eoeo.momentwo.subAlbum.application.port.out;

import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubAlbumJpaRepository extends JpaRepository<SubAlbum, Long> {
}
