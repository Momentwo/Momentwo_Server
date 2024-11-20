package cord.eoeo.momentwo.elasticsearch.adpater.out.user;

import cord.eoeo.momentwo.elasticsearch.application.port.out.user.UserElasticsearchErRepo;
import cord.eoeo.momentwo.elasticsearch.application.port.out.user.UserElasticsearchRepo;
import cord.eoeo.momentwo.elasticsearch.domain.UserDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserElasticsearchAdapter implements UserElasticsearchRepo {
    private final UserElasticsearchErRepo userElasticsearchErRepo;

    @Override
    public void save(UserDocument entity) {
        userElasticsearchErRepo.save(entity);
    }

    @Override
    public Optional<UserDocument> findById(Long id) {
        return userElasticsearchErRepo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        userElasticsearchErRepo.deleteById(id);
    }
}
