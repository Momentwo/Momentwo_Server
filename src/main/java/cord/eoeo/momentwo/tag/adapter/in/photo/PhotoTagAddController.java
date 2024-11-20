package cord.eoeo.momentwo.tag.adapter.in.photo;

import cord.eoeo.momentwo.tag.adapter.dto.in.photo.PhotoTagAddRequestDto;
import cord.eoeo.momentwo.tag.application.port.in.photo.PhotoTagAddUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PhotoTagAddController {
    private final PhotoTagAddUseCase photoTagAddUseCase;

    @PostMapping("/photos/tags")
    @ResponseStatus(HttpStatus.CREATED)
    public void photoTagAdd(@RequestBody @Valid PhotoTagAddRequestDto photoTagAddRequestDto) {
        photoTagAddUseCase.photoTagAdd(photoTagAddRequestDto);
    }
}
