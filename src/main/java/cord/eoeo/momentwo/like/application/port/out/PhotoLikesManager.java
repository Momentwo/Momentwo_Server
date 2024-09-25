package cord.eoeo.momentwo.like.application.port.out;

import cord.eoeo.momentwo.like.domain.PhotoLike;

public interface PhotoLikesManager {
    void doLikes(long photoId);
    void undoLikes(long photoId);
    PhotoLike getLikes(long photoId);
}
