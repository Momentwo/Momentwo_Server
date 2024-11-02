package cord.eoeo.momentwo.user.adapter.in.info;

import cord.eoeo.momentwo.user.adapter.dto.in.UserProfileUploadRequestDto;
import cord.eoeo.momentwo.user.application.port.in.profile.UserProfileImageUploadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserProfileUploadController {
    private final UserProfileImageUploadUseCase userProfileImageUploadUseCase;

    // 유저 프로필 업로드
    @PostMapping("/users/profiles/images/upload")
    @ResponseStatus(HttpStatus.OK)
    public void usersProfilesUpload(@ModelAttribute @Valid UserProfileUploadRequestDto userProfileUploadRequestDto) {
        userProfileImageUploadUseCase.usersProfilesImageUpload(userProfileUploadRequestDto);
    }
}
