package cord.eoeo.momentwo.elasticsearch.application.port.out;

import cord.eoeo.momentwo.elasticsearch.domain.FriendsDocument;
import cord.eoeo.momentwo.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FriendsElasticSearchManager {
    void save(long id, String toNickname);
    Page<FriendsDocument> getFriendsPaging(String keyword, User user, Pageable pageable);
    void deleteById(long id);
}
