package cord.eoeo.momentwo.tag.adapter.out.photo;

import cord.eoeo.momentwo.tag.application.port.out.photo.PhotoTagGenericJpaRepo;
import cord.eoeo.momentwo.tag.application.port.out.photo.PhotoTagGenericRepo;
import cord.eoeo.momentwo.tag.domain.PhotoTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PhotoTagGenericAdapter implements PhotoTagGenericRepo {
    private final PhotoTagGenericJpaRepo photoTagGenericJpaRepo;

    @Override
    public void save(PhotoTag entity) {
        photoTagGenericJpaRepo.save(entity);
    }

    @Override
    public Optional<PhotoTag> findById(Long id) {
        return photoTagGenericJpaRepo.findById(id);
    }

    @Override
    public List<PhotoTag> findAll() {
        return photoTagGenericJpaRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        photoTagGenericJpaRepo.deleteById(id);
    }
}
