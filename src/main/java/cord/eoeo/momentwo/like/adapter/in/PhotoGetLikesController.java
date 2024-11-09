package cord.eoeo.momentwo.like.adapter.in;

import cord.eoeo.momentwo.like.adapter.dto.out.PhotoLikesResponseDto;
import cord.eoeo.momentwo.like.application.port.in.GetLikesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PhotoGetLikesController {
    private final GetLikesUseCase getLikesUseCase;

    @GetMapping("/photo/likes/{albumId}/{photoId}")
    @ResponseStatus(HttpStatus.OK)
    public PhotoLikesResponseDto getLikes(
            @PathVariable long albumId,
            @PathVariable long photoId
    ) {
        return getLikesUseCase.getLikes(albumId, photoId);
    }
}
