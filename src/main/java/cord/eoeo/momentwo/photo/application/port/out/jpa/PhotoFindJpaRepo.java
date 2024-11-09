package cord.eoeo.momentwo.photo.application.port.out.jpa;

import cord.eoeo.momentwo.photo.application.port.out.PhotoGenericJpaRepo;
import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.user.domain.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PhotoFindJpaRepo extends PhotoGenericJpaRepo {
    @Query("SELECT p FROM Photo p WHERE p.id = :id AND p.user = :user")
    Optional<Photo> findByIdAndUser(long id, User user);
}
