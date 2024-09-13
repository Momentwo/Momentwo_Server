package cord.eoeo.momentwo.elasticsearch.application.service;

import cord.eoeo.momentwo.elasticsearch.application.port.out.UserSearchRepository;
import cord.eoeo.momentwo.elasticsearch.domain.UserDocument;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class UserElasticSearchManager {
    private final UserSearchRepository userSearchRepository;

    public void save(User user) {
        UserDocument userDocument = new UserDocument(user);
        userSearchRepository.save(userDocument);
    }

    public List<UserDocument> getMembers(String keyword, User user) {
        return userSearchRepository.findByNickname(keyword).stream()
                .filter(userDocument -> !Objects.equals(userDocument.getId(), user.getId()))
                .collect(Collectors.toList());
    }
}
