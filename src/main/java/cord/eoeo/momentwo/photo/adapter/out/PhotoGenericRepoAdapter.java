package cord.eoeo.momentwo.photo.adapter.out;

import cord.eoeo.momentwo.photo.application.port.out.PhotoGenericJpaRepo;
import cord.eoeo.momentwo.photo.application.port.out.PhotoGenericRepo;
import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PhotoGenericRepoAdapter implements PhotoGenericRepo {
    private final PhotoGenericJpaRepo photoGenericJpaRepo;

    @Override
    public void save(Photo entity) {
        photoGenericJpaRepo.save(entity);
    }

    @Override
    public Optional<Photo> findById(Long id) {
        return photoGenericJpaRepo.findById(id);
    }

    @Override
    public List<Photo> findAll() {
        return photoGenericJpaRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        photoGenericJpaRepo.deleteById(id);
    }
}
