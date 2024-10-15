package cord.eoeo.momentwo.image.adapter.in;

import cord.eoeo.momentwo.image.adapter.dto.PresignedRequestDto;
import cord.eoeo.momentwo.image.adapter.dto.PresignedResponseDto;
import cord.eoeo.momentwo.image.application.port.in.ImageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {
    private final ImageUseCase imageUseCase;

    @PostMapping("/photos/presigned")
    @ResponseStatus(HttpStatus.OK)
    public PresignedResponseDto photoPresignedUrl(@RequestBody @Valid PresignedRequestDto presignedRequestDto) {
        return imageUseCase.photoPresignedUrl(presignedRequestDto);
    }

    @PostMapping("/albums/profiles/presigned")
    @ResponseStatus(HttpStatus.OK)
    public PresignedResponseDto albumProfilePresignedUrl(@RequestBody @Valid PresignedRequestDto presignedRequestDto) {
        return imageUseCase.albumProfilePresignedUrl(presignedRequestDto);
    }
}
