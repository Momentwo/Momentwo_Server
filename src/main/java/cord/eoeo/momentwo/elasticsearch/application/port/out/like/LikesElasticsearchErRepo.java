package cord.eoeo.momentwo.elasticsearch.application.port.out.like;

import cord.eoeo.momentwo.elasticsearch.domain.LikesDocument;
import cord.eoeo.momentwo.global.application.port.out.ElasticsearchGenericDefaultJpaRepo;

public interface LikesElasticsearchErRepo extends ElasticsearchGenericDefaultJpaRepo<LikesDocument, Long> {
}
