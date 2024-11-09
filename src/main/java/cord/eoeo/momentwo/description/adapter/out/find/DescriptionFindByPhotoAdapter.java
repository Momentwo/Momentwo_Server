package cord.eoeo.momentwo.description.adapter.out.find;

import cord.eoeo.momentwo.description.application.port.out.find.DescriptionFindByPhotoRepo;
import cord.eoeo.momentwo.description.application.port.out.jpa.DescriptionFindJpaRepo;
import cord.eoeo.momentwo.description.domain.Description;
import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DescriptionFindByPhotoAdapter implements DescriptionFindByPhotoRepo {
    private final DescriptionFindJpaRepo descriptionFindJpaRepo;

    @Override
    public Optional<Description> findByPhoto(Photo photo) {
        return descriptionFindJpaRepo.findByPhoto(photo);
    }
}
