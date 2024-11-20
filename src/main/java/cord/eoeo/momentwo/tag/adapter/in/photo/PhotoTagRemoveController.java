package cord.eoeo.momentwo.tag.adapter.in.photo;

import cord.eoeo.momentwo.tag.adapter.dto.in.photo.PhotoTagRemoveRequestDto;
import cord.eoeo.momentwo.tag.application.port.in.photo.PhotoTagRemoveUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PhotoTagRemoveController {
    private final PhotoTagRemoveUseCase photoTagRemoveUseCase;

    @DeleteMapping("/photos/tags")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void photoTagRemove(@RequestBody @Valid PhotoTagRemoveRequestDto photoTagRemoveRequestDto) {
        photoTagRemoveUseCase.photoTagRemove(photoTagRemoveRequestDto);
    }
}
