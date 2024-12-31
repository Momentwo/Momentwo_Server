package cord.eoeo.momentwo.description.adapter.in;

import cord.eoeo.momentwo.description.application.port.in.DescriptionDeleteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DescriptionDeleteController {
    private final DescriptionDeleteUseCase descriptionDeleteUseCase;

    @DeleteMapping("/albums/{albumId}/sub/photos/{photoId}/descriptions")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDescription(@PathVariable Long albumId, @PathVariable Long photoId) {
        descriptionDeleteUseCase.deleteDescription(albumId, photoId);
    }
}
