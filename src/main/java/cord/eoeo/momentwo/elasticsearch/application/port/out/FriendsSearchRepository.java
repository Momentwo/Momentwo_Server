package cord.eoeo.momentwo.elasticsearch.application.port.out;

import cord.eoeo.momentwo.elasticsearch.domain.FriendsDocument;

import java.util.List;

public interface FriendsSearchRepository {
    void save(FriendsDocument friendsDocument);

    List<FriendsDocument> getFriends(String nickname);
}
