package cord.eoeo.momentwo.elasticsearch.application.port.out;

import cord.eoeo.momentwo.elasticsearch.domain.LikesDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LikesElasticSearchRepository extends ElasticsearchRepository<LikesDocument, Long> {
}
