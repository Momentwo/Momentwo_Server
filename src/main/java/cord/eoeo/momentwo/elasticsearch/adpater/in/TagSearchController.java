package cord.eoeo.momentwo.elasticsearch.adpater.in;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.TagSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.TagSearchResponseDto;
import cord.eoeo.momentwo.elasticsearch.application.port.in.TagSearchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class TagSearchController {
    private final TagSearchUseCase tagSearchUseCase;

    @GetMapping("/albums/tags/search")
    @ResponseStatus(HttpStatus.OK)
    public TagSearchResponseDto getAlbumTags(@ModelAttribute @Valid TagSearchRequestDto tagSearchRequestDto) {
        return tagSearchUseCase.getAlbumTags(tagSearchRequestDto);
    }
}
