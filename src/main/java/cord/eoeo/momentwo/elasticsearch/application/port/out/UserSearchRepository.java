package cord.eoeo.momentwo.elasticsearch.application.port.out;

import cord.eoeo.momentwo.elasticsearch.domain.UserDocument;

import java.util.Optional;

public interface UserSearchRepository {
    void save(UserDocument userDocument);
    Optional<UserDocument> findById(long id);
}
