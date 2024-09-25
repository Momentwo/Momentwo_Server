package cord.eoeo.momentwo.elasticsearch.adpater.dto.out;

import cord.eoeo.momentwo.elasticsearch.domain.LikesDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikesSearchResponseDto {
    private String nickname;

    public LikesSearchResponseDto toDo(LikesDocument likesDocument) {
        return new LikesSearchResponseDto(likesDocument.getNickname());
    }
}
