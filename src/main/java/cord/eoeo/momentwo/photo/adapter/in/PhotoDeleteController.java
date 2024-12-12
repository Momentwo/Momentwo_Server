package cord.eoeo.momentwo.photo.adapter.in;

import cord.eoeo.momentwo.photo.application.port.in.PhotoDeleteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PhotoDeleteController {
    private final PhotoDeleteUseCase photoDeleteUseCase;

    // 삭제
    @DeleteMapping("/albums/{albumId}/sub/{subAlbumId}/photos/{imagesId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void photoDelete(
            @PathVariable Long albumId,
            @PathVariable Long subAlbumId,
            @PathVariable List<Long> imagesId
    ) {
        photoDeleteUseCase.photoDelete(albumId, subAlbumId, imagesId);
    }
}
