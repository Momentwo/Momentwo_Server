package cord.eoeo.momentwo.description.adapter.out.delete;

import cord.eoeo.momentwo.description.application.port.out.delete.DescriptionDeleteByPhotoRepo;
import cord.eoeo.momentwo.description.application.port.out.jpa.DescriptionDeleteJpaRepo;
import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DescriptionDeleteByPhotoAdapter implements DescriptionDeleteByPhotoRepo {
    private final DescriptionDeleteJpaRepo descriptionDeleteJpaRepo;

    @Override
    public void deleteByPhoto(Photo photo) {
        descriptionDeleteJpaRepo.deleteByPhoto(photo);
    }
}
