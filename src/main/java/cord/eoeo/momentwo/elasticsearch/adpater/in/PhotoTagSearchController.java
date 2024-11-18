package cord.eoeo.momentwo.elasticsearch.adpater.in;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.photo.PhotoTagSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.photo.PhotoTagSearchListResponseDto;
import cord.eoeo.momentwo.elasticsearch.application.port.in.photo.PhotoTagSearchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PhotoTagSearchController {
    private final PhotoTagSearchUseCase photoTagSearchUseCase;

    @GetMapping("/photos/tags/search")
    @ResponseStatus(HttpStatus.OK)
    public PhotoTagSearchListResponseDto photoTagSearch(
            @ModelAttribute @Valid PhotoTagSearchRequestDto photoTagSearchRequestDto) {
        return photoTagSearchUseCase.photoTagSearch(photoTagSearchRequestDto);
    }
}
