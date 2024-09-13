package cord.eoeo.momentwo.elasticsearch.adpater.dto.out;

import cord.eoeo.momentwo.elasticsearch.domain.UserDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchResponseDto {
    private long id;
    private String nickname;

    public UserSearchResponseDto toDo(UserDocument userDocument) {
        return new UserSearchResponseDto(
                this.id = userDocument.getId(),
                this.nickname = userDocument.getNickname()
        );
    }
}
