package cord.eoeo.momentwo.elasticsearch.application.port.out;

import cord.eoeo.momentwo.elasticsearch.domain.UserDocument;
import cord.eoeo.momentwo.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserElasticSearchManager {
    void save(User user);
    Page<UserDocument> getUsersPaging(String keyword, User user, Pageable pageable);
    void deleteById(long id);
}
