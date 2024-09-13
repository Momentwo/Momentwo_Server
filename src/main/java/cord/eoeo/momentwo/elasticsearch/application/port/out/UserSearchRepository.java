package cord.eoeo.momentwo.elasticsearch.application.port.out;

import cord.eoeo.momentwo.elasticsearch.domain.UserDocument;

import java.util.List;

public interface UserSearchRepository {
    List<UserDocument> findByNickname(String nickname);

    void save(UserDocument userDocument);
}
