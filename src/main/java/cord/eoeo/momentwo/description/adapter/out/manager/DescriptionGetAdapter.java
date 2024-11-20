package cord.eoeo.momentwo.description.adapter.out.manager;

import cord.eoeo.momentwo.description.adapter.dto.out.DescriptionResponseDto;
import cord.eoeo.momentwo.description.advice.exception.NotFoundDescriptionException;
import cord.eoeo.momentwo.description.application.port.out.find.DescriptionFindByPhotoRepo;
import cord.eoeo.momentwo.description.application.port.out.manager.DescriptionGetPort;
import cord.eoeo.momentwo.description.domain.Description;
import cord.eoeo.momentwo.photo.advice.exception.NotFoundPhotoException;
import cord.eoeo.momentwo.photo.application.port.out.PhotoGenericRepo;
import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DescriptionGetAdapter implements DescriptionGetPort {
    private final PhotoGenericRepo photoGenericRepo;
    private final DescriptionFindByPhotoRepo descriptionFindByPhotoRepo;

    @Override
    public DescriptionResponseDto getDescription(long photoId) {
        Photo photo = photoGenericRepo.findById(photoId).orElseThrow(NotFoundPhotoException::new);

        Description description = descriptionFindByPhotoRepo.findByPhoto(photo)
                .orElseThrow(NotFoundDescriptionException::new);

        return new DescriptionResponseDto().toDo(description);
    }
}
