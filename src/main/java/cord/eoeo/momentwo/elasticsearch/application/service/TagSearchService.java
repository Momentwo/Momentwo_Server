package cord.eoeo.momentwo.elasticsearch.application.service;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.TagSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.adpater.dto.out.TagSearchResponseDto;
import cord.eoeo.momentwo.elasticsearch.application.port.in.TagSearchUseCase;
import cord.eoeo.momentwo.elasticsearch.application.port.out.tag.manager.GetAlbumTagsPort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagSearchService implements TagSearchUseCase {
    private final GetAlbumTagsPort getAlbumTagsPort;

    @Override
    @CheckAlbumAccessRules
    public TagSearchResponseDto getAlbumTags(TagSearchRequestDto tagSearchRequestDto) {
        return new TagSearchResponseDto().toDo(getAlbumTagsPort.getAlbumTags(tagSearchRequestDto));
    }
}
