package cord.eoeo.momentwo.description.adapter.out.manager;

import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionCreateRequestDto;
import cord.eoeo.momentwo.description.advice.exception.NotCreateDescriptionException;
import cord.eoeo.momentwo.description.application.port.out.DescriptionGenericRepo;
import cord.eoeo.momentwo.description.application.port.out.find.DescriptionFindByPhotoRepo;
import cord.eoeo.momentwo.description.application.port.out.manager.DescriptionCreatePort;
import cord.eoeo.momentwo.description.application.port.out.manager.DescriptionGetPhotoPort;
import cord.eoeo.momentwo.description.domain.Description;
import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DescriptionCreateAdapter implements DescriptionCreatePort {
    private final DescriptionGetPhotoPort descriptionGetPhotoPort;
    private final DescriptionFindByPhotoRepo descriptionFindByPhotoRepo;
    private final DescriptionGenericRepo descriptionGenericRepo;

    @Override
    public void createDescription(DescriptionCreateRequestDto descriptionCreateRequestDto) {
        Photo photo = descriptionGetPhotoPort.getPhoto(descriptionCreateRequestDto.getPhotoId());

        descriptionFindByPhotoRepo.findByPhoto(photo).ifPresentOrElse(
                description -> {
                    throw new NotCreateDescriptionException();
                },
                () -> {
                    Description description = new Description(
                            descriptionCreateRequestDto.getDescription(),
                            photo
                    );

                    descriptionGenericRepo.save(description);
                }
        );
    }
}
