package cord.eoeo.momentwo.elasticsearch.application.port.out;

import cord.eoeo.momentwo.elasticsearch.domain.UserDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserSearchElasticRepository extends ElasticsearchRepository<UserDocument, Long> {
    @Query("{\"wildcard\": {\"nickname\": {\"value\": \"?0*\"}}}")
    List<UserDocument> findByNickname(String nickname);
}
