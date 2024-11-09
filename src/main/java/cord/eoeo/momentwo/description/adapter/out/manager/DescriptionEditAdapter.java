package cord.eoeo.momentwo.description.adapter.out.manager;

import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionEditRequestDto;
import cord.eoeo.momentwo.description.advice.exception.NotFoundDescriptionException;
import cord.eoeo.momentwo.description.application.port.out.DescriptionGenericRepo;
import cord.eoeo.momentwo.description.application.port.out.find.DescriptionFindByPhotoRepo;
import cord.eoeo.momentwo.description.application.port.out.manager.DescriptionEditPort;
import cord.eoeo.momentwo.description.application.port.out.manager.DescriptionGetPhotoPort;
import cord.eoeo.momentwo.description.domain.Description;
import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DescriptionEditAdapter implements DescriptionEditPort {
    private final DescriptionGetPhotoPort descriptionGetPhotoPort;
    private final DescriptionFindByPhotoRepo descriptionFindByPhotoRepo;
    private final DescriptionGenericRepo descriptionGenericRepo;

    @Override
    public void editDescription(DescriptionEditRequestDto descriptionEditRequestDto) {
        Photo photo = descriptionGetPhotoPort.getPhoto(descriptionEditRequestDto.getPhotoId());

        Description description = descriptionFindByPhotoRepo.findByPhoto(photo)
                .orElseThrow(NotFoundDescriptionException::new);

        description.setDescription(descriptionEditRequestDto.getEditDescription());
        descriptionGenericRepo.save(description);
    }
}
