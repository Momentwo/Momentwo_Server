package cord.eoeo.momentwo.photo.application.port.out;

import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.user.domain.User;

public interface MoveCheckAdminOrSelfPort {
    void moveCheckAdminOrSelf(Photo photo, User user);
}
