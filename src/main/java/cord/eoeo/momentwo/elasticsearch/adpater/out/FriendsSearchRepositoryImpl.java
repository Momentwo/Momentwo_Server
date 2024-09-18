package cord.eoeo.momentwo.elasticsearch.adpater.out;

import cord.eoeo.momentwo.elasticsearch.application.port.out.FriendsElasticSearchRepository;
import cord.eoeo.momentwo.elasticsearch.application.port.out.FriendsSearchRepository;
import cord.eoeo.momentwo.elasticsearch.domain.FriendsDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FriendsSearchRepositoryImpl implements FriendsSearchRepository {
    private final FriendsElasticSearchRepository friendsElasticSearchRepository;

    @Override
    public void save(FriendsDocument friendsDocument) {
        friendsElasticSearchRepository.save(friendsDocument);
    }

    @Override
    public List<FriendsDocument> getFriends(String nickname) {
        return friendsElasticSearchRepository.getFriends(nickname);
    }
}
