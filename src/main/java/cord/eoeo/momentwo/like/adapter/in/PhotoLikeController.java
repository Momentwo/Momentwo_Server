package cord.eoeo.momentwo.like.adapter.in;

import cord.eoeo.momentwo.like.adapter.dto.in.PhotoLikesRequestDto;
import cord.eoeo.momentwo.like.adapter.dto.out.PhotoLikesResponseDto;
import cord.eoeo.momentwo.like.application.port.in.PhotoLikeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/photo/likes")
public class PhotoLikeController {
    private final PhotoLikeUseCase photoLikeUseCase;

    @PostMapping("/do")
    @ResponseStatus(HttpStatus.OK)
    public void doLikes(@RequestBody @Valid PhotoLikesRequestDto photoLikesRequestDto) {
        photoLikeUseCase.doLikes(photoLikesRequestDto);
    }

    @PostMapping("/undo")
    @ResponseStatus(HttpStatus.OK)
    public void undoLikes(@RequestBody @Valid PhotoLikesRequestDto photoLikesRequestDto) {
        photoLikeUseCase.undoLikes(photoLikesRequestDto);
    }

    @GetMapping("/{albumId}/{photoId}")
    @ResponseStatus(HttpStatus.OK)
    public PhotoLikesResponseDto getLikes(
            @PathVariable long albumId,
            @PathVariable long photoId
    ) {
        return photoLikeUseCase.getLikes(albumId, photoId);
    }
}
