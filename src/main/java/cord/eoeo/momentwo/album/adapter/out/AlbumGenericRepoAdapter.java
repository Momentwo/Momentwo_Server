package cord.eoeo.momentwo.album.adapter.out;

import cord.eoeo.momentwo.album.application.port.out.AlbumGenericJpaRepo;
import cord.eoeo.momentwo.album.application.port.out.AlbumGenericRepo;
import cord.eoeo.momentwo.album.domain.Album;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AlbumGenericRepoAdapter implements AlbumGenericRepo {
    private final AlbumGenericJpaRepo albumGenericJpaRepo;

    @Override
    public void save(Album entity) {
        albumGenericJpaRepo.save(entity);
    }

    @Override
    public Optional<Album> findById(Long id) {
        return albumGenericJpaRepo.findById(id);
    }

    @Override
    public List<Album> findAll() {
        return findAll();
    }

    @Override
    public void deleteById(Long id) {
        albumGenericJpaRepo.deleteById(id);
    }
}
