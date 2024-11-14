package cord.eoeo.momentwo.description.adapter.out.manager;

import cord.eoeo.momentwo.description.application.port.out.manager.DescriptionGetPhotoPort;
import cord.eoeo.momentwo.photo.advice.exception.NotPhotoAccessException;
import cord.eoeo.momentwo.photo.application.port.out.find.PhotoFindIdAndUserPort;
import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DescriptionGetPhotoAdapter implements DescriptionGetPhotoPort {
    private final UserNicknameValidPort userNicknameValidPort;
    private final PhotoFindIdAndUserPort photoFindIdAndUserPort;

    @Override
    public Photo getPhoto(long photoId) {
        User user = userNicknameValidPort.authenticationValid();

        return photoFindIdAndUserPort.findByIdAndUser(photoId, user)
                .orElseThrow(NotPhotoAccessException::new);
    }
}
