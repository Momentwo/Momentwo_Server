package cord.eoeo.momentwo.elasticsearch.adpater.out.like;

import cord.eoeo.momentwo.elasticsearch.application.port.out.like.CountByPhotoIdRepo;
import cord.eoeo.momentwo.elasticsearch.application.port.out.like.LikesElasticsearchErRepo;
import cord.eoeo.momentwo.elasticsearch.application.port.out.like.LikesElasticsearchRepo;
import cord.eoeo.momentwo.elasticsearch.domain.LikesDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LikesElasticsearchAdapter implements LikesElasticsearchRepo {
    private final LikesElasticsearchErRepo likesElasticsearchErRepo;
    private final CountByPhotoIdRepo countByPhotoIdRepo;

    @Override
    public void save(LikesDocument entity) {
        likesElasticsearchErRepo.save(entity);
    }

    @Override
    public Optional<LikesDocument> findById(Long id) {
        return likesElasticsearchErRepo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        likesElasticsearchErRepo.deleteById(id);
    }

    @Override
    public long countByPhotoId(long photoId) {
        return countByPhotoIdRepo.countByPhotoId(photoId);
    }
}
