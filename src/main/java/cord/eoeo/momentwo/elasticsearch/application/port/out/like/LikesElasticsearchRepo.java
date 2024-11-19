package cord.eoeo.momentwo.elasticsearch.application.port.out.like;

import cord.eoeo.momentwo.elasticsearch.domain.LikesDocument;
import cord.eoeo.momentwo.global.application.port.out.ElasticsearchGenericDefaultRepo;

public interface LikesElasticsearchRepo extends ElasticsearchGenericDefaultRepo<LikesDocument, String> {
    long countByPhotoId(long photoId);
}
