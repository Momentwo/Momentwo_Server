package cord.eoeo.momentwo.elasticsearch.adpater.out.user.manager;

import cord.eoeo.momentwo.elasticsearch.application.port.out.user.UserElasticsearchRepo;
import cord.eoeo.momentwo.elasticsearch.application.port.out.user.manager.UserInfoChangePort;
import cord.eoeo.momentwo.elasticsearch.domain.UserDocument;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInfoChangeAdapter implements UserInfoChangePort {
    private final UserElasticsearchRepo userElasticsearchRepo;

    @Override
    public void userInfoChange(User user) {
        UserDocument userDocument = userElasticsearchRepo.findById(user.getId())
                .orElseThrow(NotFoundUserException::new);

        userDocument.setUserProfileImage(user.getUserProfileImage());
        userElasticsearchRepo.save(userDocument);
    }
}
