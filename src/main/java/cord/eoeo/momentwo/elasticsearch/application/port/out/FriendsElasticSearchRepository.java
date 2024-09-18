package cord.eoeo.momentwo.elasticsearch.application.port.out;

import cord.eoeo.momentwo.elasticsearch.domain.FriendsDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface FriendsElasticSearchRepository extends ElasticsearchRepository<FriendsDocument, Long> {
    @Query("{\"wildcard\": {\"toNickname\": {\"value\": \"?0*\"}}}")
    List<FriendsDocument> getFriends(String nickname);
}
