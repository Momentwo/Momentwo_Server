package cord.eoeo.momentwo.elasticsearch.adpater.out;

import cord.eoeo.momentwo.elasticsearch.application.port.out.LikesElasticSearchRepository;
import cord.eoeo.momentwo.elasticsearch.application.port.out.LikesSearchRepository;
import cord.eoeo.momentwo.elasticsearch.domain.LikesDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikesSearchRepositoryImpl implements LikesSearchRepository {
    private final LikesElasticSearchRepository likesElasticSearchRepository;

    @Override
    public void save(LikesDocument likesDocument) {
        likesElasticSearchRepository.save(likesDocument);
    }

    @Override
    public long countByPhotoId(long photoId) {
        return likesElasticSearchRepository.countByPhotoId(photoId);
    }
}
