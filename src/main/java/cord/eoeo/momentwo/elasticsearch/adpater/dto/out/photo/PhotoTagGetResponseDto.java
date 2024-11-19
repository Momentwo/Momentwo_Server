package cord.eoeo.momentwo.elasticsearch.adpater.dto.out.photo;

import cord.eoeo.momentwo.elasticsearch.domain.PhotoTagsDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoTagGetResponseDto {
    private List<String> photoTags;

    public PhotoTagGetResponseDto toDo(PhotoTagsDocument photoTagsDocument) {
        return new PhotoTagGetResponseDto(
                photoTagsDocument.getPhotoTagsTerms()
        );
    }
}
