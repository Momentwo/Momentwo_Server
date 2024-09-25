package cord.eoeo.momentwo.elasticsearch.adpater.in;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.LikesSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.LikesSearchListResponseDto;
import cord.eoeo.momentwo.elasticsearch.application.port.in.LikesSearchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LikesSearchController {
    private final LikesSearchUseCase likesSearchUseCase;

    @GetMapping("/photo/likes/search")
    @ResponseStatus(HttpStatus.OK)
    public LikesSearchListResponseDto getPhotoLikes(@ModelAttribute @Valid LikesSearchRequestDto likesSearchRequestDto) {
        return likesSearchUseCase.getPhotoLikes(likesSearchRequestDto);
    }
}
