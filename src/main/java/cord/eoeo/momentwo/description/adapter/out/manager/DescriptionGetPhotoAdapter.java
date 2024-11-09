package cord.eoeo.momentwo.description.adapter.out.manager;

import cord.eoeo.momentwo.description.application.port.out.manager.DescriptionGetPhotoPort;
import cord.eoeo.momentwo.photo.advice.exception.NotPhotoAccessException;
import cord.eoeo.momentwo.photo.application.port.out.find.PhotoFindIdAndUserPort;
import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.application.port.out.find.UserFindNicknameRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DescriptionGetPhotoAdapter implements DescriptionGetPhotoPort {
    private final UserFindNicknameRepo userFindNicknameRepo;
    private final GetAuthentication getAuthentication;
    private final PhotoFindIdAndUserPort photoFindIdAndUserPort;

    @Override
    public Photo getPhoto(long photoId) {
        User user = userFindNicknameRepo.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);
        return photoFindIdAndUserPort.findByIdAndUser(photoId, user)
                .orElseThrow(NotPhotoAccessException::new);
    }
}
