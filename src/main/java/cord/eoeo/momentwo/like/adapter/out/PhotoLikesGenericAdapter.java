package cord.eoeo.momentwo.like.adapter.out;

import cord.eoeo.momentwo.like.application.port.out.PhotoLikesGenericJpaRepo;
import cord.eoeo.momentwo.like.application.port.out.PhotoLikesGenericRepo;
import cord.eoeo.momentwo.like.domain.PhotoLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PhotoLikesGenericAdapter implements PhotoLikesGenericRepo {
    private final PhotoLikesGenericJpaRepo photoLikesGenericJpaRepo;

    @Override
    public void save(PhotoLike entity) {
        photoLikesGenericJpaRepo.save(entity);
    }

    @Override
    public Optional<PhotoLike> findById(Long id) {
        return photoLikesGenericJpaRepo.findById(id);
    }

    @Override
    public List<PhotoLike> findAll() {
        return photoLikesGenericJpaRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        photoLikesGenericJpaRepo.deleteById(id);
    }
}
