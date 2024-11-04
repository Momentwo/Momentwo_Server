package cord.eoeo.momentwo.description.adapter.out;

import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionCreateRequestDto;
import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionEditRequestDto;
import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionRequestDto;
import cord.eoeo.momentwo.description.adapter.dto.out.DescriptionResponseDto;
import cord.eoeo.momentwo.description.advice.exception.NotCreateDescriptionException;
import cord.eoeo.momentwo.description.advice.exception.NotFoundDescriptionException;
import cord.eoeo.momentwo.description.application.port.out.DescriptionManager;
import cord.eoeo.momentwo.description.application.port.out.DescriptionRepository;
import cord.eoeo.momentwo.description.domain.Description;
import cord.eoeo.momentwo.photo.advice.exception.NotFoundPhotoException;
import cord.eoeo.momentwo.photo.advice.exception.NotPhotoAccessException;
import cord.eoeo.momentwo.photo.application.port.out.PhotoGenericRepo;
import cord.eoeo.momentwo.photo.application.port.out.find.PhotoFindIdAndUserPort;
import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.application.port.out.find.UserFindNicknameRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class DescriptionManagerImpl implements DescriptionManager {
    private final DescriptionRepository descriptionRepository;
    private final PhotoGenericRepo photoGenericRepo;
    private final PhotoFindIdAndUserPort photoFindIdAndUserPort;
    private final UserFindNicknameRepo userFindNicknameRepo;
    private final GetAuthentication getAuthentication;

    @Override
    @Transactional
    public void createDescription(DescriptionCreateRequestDto descriptionCreateRequestDto) {
        Photo photo = getPhoto(descriptionCreateRequestDto.getPhotoId());

        descriptionRepository.findByPhoto(photo).ifPresentOrElse(
                description -> {
                    throw new NotCreateDescriptionException();
                },
                () -> {
                    Description description = new Description(
                            descriptionCreateRequestDto.getDescription(),
                            photo
                    );

                    descriptionRepository.save(description);
                }
        );
    }

    @Override
    @Transactional
    public void editDescription(DescriptionEditRequestDto descriptionEditRequestDto) {
        Photo photo = getPhoto(descriptionEditRequestDto.getPhotoId());

        Description description = descriptionRepository.findByPhoto(photo)
                .orElseThrow(NotFoundDescriptionException::new);

        description.setDescription(descriptionEditRequestDto.getEditDescription());
        descriptionRepository.save(description);
    }

    @Override
    @Transactional
    public void deleteDescription(DescriptionRequestDto descriptionRequestDto) {
        Photo photo = getPhoto(descriptionRequestDto.getPhotoId());

        descriptionRepository.deleteByPhoto(photo);
    }

    @Override
    @Transactional(readOnly = true)
    public DescriptionResponseDto getDescription(long photoId) {
        Photo photo = photoGenericRepo.findById(photoId).orElseThrow(NotFoundPhotoException::new);

        Description description = descriptionRepository.findByPhoto(photo)
                .orElseThrow(NotFoundDescriptionException::new);

        return new DescriptionResponseDto().toDo(description);
    }

    private Photo getPhoto(long photoId) {
        User user = userFindNicknameRepo.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);
        return photoFindIdAndUserPort.findByIdAndUser(photoId, user)
                .orElseThrow(NotPhotoAccessException::new);
    }
}
