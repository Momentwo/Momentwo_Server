package cord.eoeo.momentwo.elasticsearch.adpater.out.user.manager;

import cord.eoeo.momentwo.elasticsearch.application.port.out.user.UserElasticsearchRepo;
import cord.eoeo.momentwo.elasticsearch.application.port.out.user.manager.UserSavePort;
import cord.eoeo.momentwo.elasticsearch.domain.UserDocument;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSaveAdapter implements UserSavePort {
    private final UserElasticsearchRepo userElasticsearchRepo;

    @Override
    public void userSave(User user) {
        UserDocument userDocument = new UserDocument(user);
        userElasticsearchRepo.save(userDocument);
    }
}
