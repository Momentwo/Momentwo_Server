package cord.eoeo.momentwo.elasticsearch.application.port.out;

import cord.eoeo.momentwo.elasticsearch.domain.UserDocument;

public interface UserSearchRepository {
    void save(UserDocument userDocument);
}
