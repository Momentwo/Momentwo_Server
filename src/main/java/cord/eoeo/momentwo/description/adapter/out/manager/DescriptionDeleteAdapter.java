package cord.eoeo.momentwo.description.adapter.out.manager;

import cord.eoeo.momentwo.description.application.port.out.delete.DescriptionDeleteByPhotoRepo;
import cord.eoeo.momentwo.description.application.port.out.manager.DescriptionDeletePort;
import cord.eoeo.momentwo.description.application.port.out.manager.DescriptionGetPhotoPort;
import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DescriptionDeleteAdapter implements DescriptionDeletePort {
    private final DescriptionGetPhotoPort descriptionGetPhotoPort;
    private final DescriptionDeleteByPhotoRepo descriptionDeleteByPhotoRepo;

    @Override
    public void deleteDescription(Long photoId) {
        Photo photo = descriptionGetPhotoPort.getPhoto(photoId);

        descriptionDeleteByPhotoRepo.deleteByPhoto(photo);
    }
}
