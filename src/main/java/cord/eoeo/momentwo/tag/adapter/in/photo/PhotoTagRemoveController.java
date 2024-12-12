package cord.eoeo.momentwo.tag.adapter.in.photo;

import cord.eoeo.momentwo.tag.application.port.in.photo.PhotoTagRemoveUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PhotoTagRemoveController {
    private final PhotoTagRemoveUseCase photoTagRemoveUseCase;

    @DeleteMapping("/albums/{albumId}/sub/photos/{photoId}/tags/{photoTagId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void photoTagRemove(@PathVariable Long albumId, @PathVariable Long photoId, @PathVariable Long photoTagId) {
        photoTagRemoveUseCase.photoTagRemove(albumId, photoId, photoTagId);
    }
}
