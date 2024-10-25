package cord.eoeo.momentwo.user.adapter.in;

import cord.eoeo.momentwo.user.adapter.dto.in.ChangePasswordRequestDto;
import cord.eoeo.momentwo.user.adapter.dto.in.SearchUsernameRequestDto;
import cord.eoeo.momentwo.user.adapter.dto.in.TempPasswordRequestDto;
import cord.eoeo.momentwo.user.adapter.dto.in.UserProfileUploadRequestDto;
import cord.eoeo.momentwo.user.adapter.dto.out.SearchUsernameResponseDto;
import cord.eoeo.momentwo.user.adapter.dto.out.TempPasswordResponseDto;
import cord.eoeo.momentwo.user.application.port.in.UserInfoChangeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserInfoChangeController {
    private final UserInfoChangeUseCase userInfoChangeUseCase;

    // 아이디 찾기
    @PostMapping("/search/username")
    @ResponseStatus(HttpStatus.OK)
    public SearchUsernameResponseDto searchUsername(@RequestBody @Valid SearchUsernameRequestDto searchUsernameRequestDto) {
        return userInfoChangeUseCase.searchUsername(searchUsernameRequestDto);
    }

    // 임시 비밀번호 발급 -> 비밀번호 찾기 시 적용
    @PostMapping("/temp/password")
    @ResponseStatus(HttpStatus.OK)
    public TempPasswordResponseDto tempPassword(@RequestBody @Valid TempPasswordRequestDto tempPasswordRequestDto) {
        return userInfoChangeUseCase.tempPassword(tempPasswordRequestDto);
    }

    // 비밀번호 변경
    @PostMapping("/change/password")
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(@RequestBody @Valid ChangePasswordRequestDto changePasswordRequestDto) {
        userInfoChangeUseCase.changePassword(changePasswordRequestDto);
    }

    // 유저 프로필 업로드
    @PostMapping("/users/profiles/images/upload")
    @ResponseStatus(HttpStatus.OK)
    public void usersProfilesUpload(@ModelAttribute @Valid UserProfileUploadRequestDto userProfileUploadRequestDto) {
        userInfoChangeUseCase.usersProfilesUpload(userProfileUploadRequestDto);
    }
}
