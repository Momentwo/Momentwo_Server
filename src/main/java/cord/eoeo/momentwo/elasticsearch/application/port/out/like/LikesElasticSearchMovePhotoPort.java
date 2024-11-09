package cord.eoeo.momentwo.elasticsearch.application.port.out.like;

import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.user.domain.User;

public interface LikesElasticSearchMovePhotoPort{
    void editSubAlbumId(Photo photo, User user, long moveSubAlbumId);
}
