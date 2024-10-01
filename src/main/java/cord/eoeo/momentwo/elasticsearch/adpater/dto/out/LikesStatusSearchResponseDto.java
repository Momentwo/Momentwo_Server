package cord.eoeo.momentwo.elasticsearch.adpater.dto.out;

import cord.eoeo.momentwo.elasticsearch.domain.LikesDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikesStatusSearchResponseDto {
    private long albumId;
    private long subAlbumId;
    private long photoId;
    private String nickname;
    private boolean likesStatus;

    public LikesStatusSearchResponseDto toDo(LikesDocument likesDocument) {
        return new LikesStatusSearchResponseDto(
                likesDocument.getAlbumId(),
                likesDocument.getSubAlbumId(),
                likesDocument.getPhotoId(),
                likesDocument.getNickname(),
                true
        );
    }
}
