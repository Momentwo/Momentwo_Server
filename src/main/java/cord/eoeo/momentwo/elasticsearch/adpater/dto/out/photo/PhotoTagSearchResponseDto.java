package cord.eoeo.momentwo.elasticsearch.adpater.dto.out.photo;

import cord.eoeo.momentwo.elasticsearch.domain.PhotoTagsDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoTagSearchResponseDto {
    private long albumId;
    private long photoId;
    private String imageUrl;
    private List<String> photoTags;

    public PhotoTagSearchResponseDto toDo(PhotoTagsDocument photoTagsDocument) {
        return new PhotoTagSearchResponseDto(
                this.albumId = photoTagsDocument.getAlbumId(),
                this.photoId = photoTagsDocument.getPhotoId(),
                this.imageUrl = photoTagsDocument.getImageUrl(),
                this.photoTags = photoTagsDocument.getPhotoTagsTerms()
        );
    }
}
