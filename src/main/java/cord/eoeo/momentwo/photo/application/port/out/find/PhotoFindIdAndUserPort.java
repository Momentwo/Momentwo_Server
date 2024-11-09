package cord.eoeo.momentwo.photo.application.port.out.find;

import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.user.domain.User;

import java.util.Optional;

public interface PhotoFindIdAndUserPort {
    Optional<Photo> findByIdAndUser(long id, User user);
}
