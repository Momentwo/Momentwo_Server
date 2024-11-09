package cord.eoeo.momentwo.photo.adapter.in;

import cord.eoeo.momentwo.photo.adapter.dto.PhotoUploadRequestDto;
import cord.eoeo.momentwo.photo.application.port.in.PhotoUploadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PhotoUploadController {
    private final PhotoUploadUseCase photoUploadUseCase;

    // 업로드
    @PostMapping("/photos/upload")
    @ResponseStatus(HttpStatus.OK)
    public void photoUpload(@RequestBody @Valid PhotoUploadRequestDto photoUploadRequestDto) {
        photoUploadUseCase.photoUpload(photoUploadRequestDto);
    }
}
