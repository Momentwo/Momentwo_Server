package cord.eoeo.momentwo.elasticsearch.application.port.out;

import cord.eoeo.momentwo.elasticsearch.domain.UserDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserSearchElasticRepository extends ElasticsearchRepository<UserDocument, Long> {
}
