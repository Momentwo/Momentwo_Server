package cord.eoeo.momentwo.like.adapter.in;

import cord.eoeo.momentwo.like.adapter.dto.in.PhotoLikesRequestDto;
import cord.eoeo.momentwo.like.application.port.in.UnDoLikesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PhotoUnDoLikesController {
    private final UnDoLikesUseCase unDoLikesUseCase;

    @PostMapping("/photo/likes/undo")
    @ResponseStatus(HttpStatus.OK)
    public void undoLikes(@RequestBody @Valid PhotoLikesRequestDto photoLikesRequestDto) {
        unDoLikesUseCase.undoLikes(photoLikesRequestDto);
    }

}
