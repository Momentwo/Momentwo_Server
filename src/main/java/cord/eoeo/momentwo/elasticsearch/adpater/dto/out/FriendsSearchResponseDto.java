package cord.eoeo.momentwo.elasticsearch.adpater.dto.out;

import cord.eoeo.momentwo.elasticsearch.domain.FriendsDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendsSearchResponseDto {
    private long id;
    private String nickname;

    public FriendsSearchResponseDto toDo(FriendsDocument friendsDocument) {
        return new FriendsSearchResponseDto(
                this.id = friendsDocument.getId(),
                this.nickname = friendsDocument.getNickname()
        );
    }
}
