package cord.eoeo.momentwo.elasticsearch.adpater.dto.out;

import cord.eoeo.momentwo.elasticsearch.domain.TagsDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagSearchResponseDto {
    private long id;
    private List<String> albumTags;

    public TagSearchResponseDto toDo(TagsDocument tagsDocument) {
        return new TagSearchResponseDto(
                this.id = tagsDocument.getAlbumId(),
                this.albumTags = tagsDocument.getAlbumTagsTerms()
        );
    }
}
