package cord.eoeo.momentwo.photo.adapter.in;

import cord.eoeo.momentwo.photo.adapter.dto.PhotoDeleteRequestDto;
import cord.eoeo.momentwo.photo.application.port.in.PhotoDeleteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PhotoDeleteController {
    private final PhotoDeleteUseCase photoDeleteUseCase;
    // 삭제
    @DeleteMapping("/photos/delete")
    @ResponseStatus(HttpStatus.OK)
    public void photoDelete(@RequestBody @Valid PhotoDeleteRequestDto photoDeleteRequestDto) {
        photoDeleteUseCase.photoDelete(photoDeleteRequestDto);
    }
}
