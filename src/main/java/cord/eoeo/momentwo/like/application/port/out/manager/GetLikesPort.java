package cord.eoeo.momentwo.like.application.port.out.manager;

import cord.eoeo.momentwo.like.domain.PhotoLike;

public interface GetLikesPort {
    PhotoLike getLikes(long photoId);
}
