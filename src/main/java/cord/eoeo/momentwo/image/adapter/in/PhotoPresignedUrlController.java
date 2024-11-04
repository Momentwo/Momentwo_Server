package cord.eoeo.momentwo.image.adapter.in;

import cord.eoeo.momentwo.image.adapter.dto.PresignedRequestDto;
import cord.eoeo.momentwo.image.adapter.dto.PresignedResponseDto;
import cord.eoeo.momentwo.image.application.port.in.PhotoPresignedUrlUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PhotoPresignedUrlController {
    private final PhotoPresignedUrlUseCase photoPresignedUrlUseCase;

    @PostMapping("/images/photos/presigned")
    @ResponseStatus(HttpStatus.OK)
    public PresignedResponseDto photoPresignedUrl(@RequestBody @Valid PresignedRequestDto presignedRequestDto) {
        return photoPresignedUrlUseCase.photoPresignedUrl(presignedRequestDto);
    }
}
