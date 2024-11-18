package cord.eoeo.momentwo.elasticsearch.adpater.out.tag.manager;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.TagSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.advice.tag.exception.NotFoundAlbumTagsException;
import cord.eoeo.momentwo.elasticsearch.application.port.out.tag.TagsElasticsearchRepo;
import cord.eoeo.momentwo.elasticsearch.application.port.out.tag.manager.GetAlbumTagsPort;
import cord.eoeo.momentwo.elasticsearch.domain.TagsDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAlbumTagsAdapter implements GetAlbumTagsPort {
    private final TagsElasticsearchRepo tagsElasticsearchRepo;

    @Override
    public TagsDocument getAlbumTags(TagSearchRequestDto tagSearchRequestDto) {
        return tagsElasticsearchRepo.findById("tags_" + tagSearchRequestDto.getAlbumId())
                .orElseThrow(NotFoundAlbumTagsException::new);
    }
}
