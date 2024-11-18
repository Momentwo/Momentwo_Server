package cord.eoeo.momentwo.elasticsearch.application.port.in;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.TagSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.TagSearchResponseDto;

public interface TagSearchUseCase {
    TagSearchResponseDto getAlbumTags(TagSearchRequestDto tagSearchRequestDto);
}
