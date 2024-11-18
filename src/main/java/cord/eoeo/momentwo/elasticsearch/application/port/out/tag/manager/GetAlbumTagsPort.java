package cord.eoeo.momentwo.elasticsearch.application.port.out.tag.manager;

import cord.eoeo.momentwo.elasticsearch.adpater.dto.in.TagSearchRequestDto;
import cord.eoeo.momentwo.elasticsearch.domain.TagsDocument;

public interface GetAlbumTagsPort {
    TagsDocument getAlbumTags(TagSearchRequestDto tagSearchRequestDto);
}
