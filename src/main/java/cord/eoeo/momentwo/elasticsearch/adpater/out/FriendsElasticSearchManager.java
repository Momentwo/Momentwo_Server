package cord.eoeo.momentwo.elasticsearch.adpater.out;

import cord.eoeo.momentwo.elasticsearch.application.port.out.FriendsSearchRepository;
import cord.eoeo.momentwo.elasticsearch.domain.FriendsDocument;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class FriendsElasticSearchManager {
    private final FriendsSearchRepository friendsSearchRepository;

    public void save(long id, String toNickname) {
        FriendsDocument friendsDocument = new FriendsDocument(id, toNickname);
        friendsSearchRepository.save(friendsDocument);
    }

    public List<FriendsDocument> getFriends(String keyword, User user) {
        return friendsSearchRepository.getFriends(keyword).stream()
                .filter(friendsDocument -> Objects.equals(friendsDocument.getId(), user.getId()))
                .collect(Collectors.toList());
    }
}
