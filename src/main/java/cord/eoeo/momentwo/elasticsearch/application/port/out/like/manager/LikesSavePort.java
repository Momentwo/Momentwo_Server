package cord.eoeo.momentwo.elasticsearch.application.port.out.like.manager;

import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.user.domain.User;

public interface LikesSavePort {
    void save(User user, Photo photo);
}
