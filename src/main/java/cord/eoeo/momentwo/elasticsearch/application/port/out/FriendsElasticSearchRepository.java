package cord.eoeo.momentwo.elasticsearch.application.port.out;

import cord.eoeo.momentwo.elasticsearch.domain.FriendsDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface FriendsElasticSearchRepository extends ElasticsearchRepository<FriendsDocument, Long> {
}
