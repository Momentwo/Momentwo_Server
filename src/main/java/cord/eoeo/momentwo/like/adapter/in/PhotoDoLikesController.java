package cord.eoeo.momentwo.like.adapter.in;

import cord.eoeo.momentwo.like.adapter.dto.in.PhotoLikesRequestDto;
import cord.eoeo.momentwo.like.application.port.in.DoLikesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PhotoDoLikesController {
    private final DoLikesUseCase doLikesUseCase;

    @PostMapping("/photo/likes/do")
    @ResponseStatus(HttpStatus.OK)
    public void doLikes(@RequestBody @Valid PhotoLikesRequestDto photoLikesRequestDto) {
        doLikesUseCase.doLikes(photoLikesRequestDto);
    }

}
