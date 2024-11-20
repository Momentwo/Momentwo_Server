package cord.eoeo.momentwo.tag.adapter.out.album;

import cord.eoeo.momentwo.tag.application.port.out.album.AlbumTagGenericJpaRepo;
import cord.eoeo.momentwo.tag.application.port.out.album.AlbumTagGenericRepo;
import cord.eoeo.momentwo.tag.domain.AlbumTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AlbumTagGenericAdapter implements AlbumTagGenericRepo {
    private final AlbumTagGenericJpaRepo albumTagGenericJpaRepo;

    @Override
    public void save(AlbumTag entity) {
        albumTagGenericJpaRepo.save(entity);
    }

    @Override
    public Optional<AlbumTag> findById(Long id) {
        return albumTagGenericJpaRepo.findById(id);
    }

    @Override
    public List<AlbumTag> findAll() {
        return albumTagGenericJpaRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        albumTagGenericJpaRepo.deleteById(id);
    }
}
