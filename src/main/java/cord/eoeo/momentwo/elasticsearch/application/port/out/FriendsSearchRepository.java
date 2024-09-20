package cord.eoeo.momentwo.elasticsearch.application.port.out;

import cord.eoeo.momentwo.elasticsearch.domain.FriendsDocument;

public interface FriendsSearchRepository {
    void save(FriendsDocument friendsDocument);
}
