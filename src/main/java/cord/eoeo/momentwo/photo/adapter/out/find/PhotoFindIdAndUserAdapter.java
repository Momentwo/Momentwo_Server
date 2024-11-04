package cord.eoeo.momentwo.photo.adapter.out.find;

import cord.eoeo.momentwo.photo.application.port.out.find.PhotoFindIdAndUserPort;
import cord.eoeo.momentwo.photo.application.port.out.jpa.PhotoFindJpaRepo;
import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PhotoFindIdAndUserAdapter implements PhotoFindIdAndUserPort {
    private final PhotoFindJpaRepo photoFindJpaRepo;

    @Override
    public Optional<Photo> findByIdAndUser(long id, User user) {
        return photoFindJpaRepo.findByIdAndUser(id, user);
    }
}
