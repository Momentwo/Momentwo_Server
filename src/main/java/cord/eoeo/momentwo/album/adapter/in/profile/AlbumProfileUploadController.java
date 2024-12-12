package cord.eoeo.momentwo.album.adapter.in.profile;

import cord.eoeo.momentwo.album.adapter.dto.in.AlbumProfileUploadRequestDto;
import cord.eoeo.momentwo.album.application.port.in.profile.ProfileUploadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AlbumProfileUploadController {
    private final ProfileUploadUseCase profileUploadUseCase;

    // 프로필 업로드 (변경)
    @PostMapping("/albums/profile/image")
    @ResponseStatus(HttpStatus.OK)
    public void profileUpload(@RequestBody @Valid AlbumProfileUploadRequestDto uploadRequestDto) {
        profileUploadUseCase.profileUpload(uploadRequestDto);
    }
}
