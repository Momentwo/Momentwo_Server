package cord.eoeo.momentwo.subAlbum.adapter.out;

import cord.eoeo.momentwo.subAlbum.application.port.out.SubAlbumGenericJpaRepo;
import cord.eoeo.momentwo.subAlbum.application.port.out.SubAlbumGenericRepo;
import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SubAlbumGenericRepoAdapter implements SubAlbumGenericRepo {
    private final SubAlbumGenericJpaRepo subAlbumGenericJpaRepo;

    @Override
    public void save(SubAlbum entity) {
        subAlbumGenericJpaRepo.save(entity);
    }

    @Override
    public Optional<SubAlbum> findById(Long id) {
        return subAlbumGenericJpaRepo.findById(id);
    }

    @Override
    public List<SubAlbum> findAll() {
        return subAlbumGenericJpaRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        subAlbumGenericJpaRepo.deleteById(id);
    }
}
