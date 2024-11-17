package cord.eoeo.momentwo.elasticsearch.application.port.out.like.manager;

import cord.eoeo.momentwo.user.domain.User;

public interface IsLikesPort {
    boolean isLikes(User user, long photoId);
}
