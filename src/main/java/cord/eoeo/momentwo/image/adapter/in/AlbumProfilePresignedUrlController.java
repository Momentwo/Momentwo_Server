package cord.eoeo.momentwo.image.adapter.in;

import cord.eoeo.momentwo.image.adapter.dto.PresignedRequestDto;
import cord.eoeo.momentwo.image.adapter.dto.PresignedResponseDto;
import cord.eoeo.momentwo.image.application.port.in.AlbumProfilePresignedUrlUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AlbumProfilePresignedUrlController {
    private final AlbumProfilePresignedUrlUseCase albumProfilePresignedUrlUseCase;

    @PostMapping("/images/albums/profiles/presigned")
    @ResponseStatus(HttpStatus.OK)
    public PresignedResponseDto albumProfilePresignedUrl(@RequestBody @Valid PresignedRequestDto presignedRequestDto) {
        return albumProfilePresignedUrlUseCase.albumProfilePresignedUrl(presignedRequestDto);
    }
}
