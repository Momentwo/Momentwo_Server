package cord.eoeo.momentwo.elasticsearch.adpater.out.like.manager;

import cord.eoeo.momentwo.elasticsearch.application.port.out.like.LikesElasticsearchRepo;
import cord.eoeo.momentwo.elasticsearch.application.port.out.like.manager.LikesSavePort;
import cord.eoeo.momentwo.elasticsearch.domain.LikesDocument;
import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikesSaveAdapter implements LikesSavePort {
    private final LikesElasticsearchRepo likesElasticsearchRepo;

    @Override
    public void save(User user, Photo photo) {
        LikesDocument likesDocument = new LikesDocument(user, photo);
        likesElasticsearchRepo.save(likesDocument);
    }
}
