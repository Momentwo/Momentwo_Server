package cord.eoeo.momentwo.elasticsearch.adpater.out;

import cord.eoeo.momentwo.elasticsearch.application.port.out.UserSearchElasticRepository;
import cord.eoeo.momentwo.elasticsearch.application.port.out.UserSearchRepository;
import cord.eoeo.momentwo.elasticsearch.domain.UserDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserSearchRepositoryImpl implements UserSearchRepository {
    private final UserSearchElasticRepository userSearchElasticRepository;

    @Override
    public List<UserDocument> findByNickname(String nickname) {
        return userSearchElasticRepository.findByNickname(nickname);
    }

    @Override
    public void save(UserDocument userDocument) {
        userSearchElasticRepository.save(userDocument);
    }
}
