package cord.eoeo.momentwo.friendship.adapter.in.dto;

import cord.eoeo.momentwo.friendship.domain.Friendship;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipAllListDto {
    private String nickname;

    public FriendshipAllListDto toDo(Friendship friendship) {
        return new FriendshipAllListDto(friendship.getFromUser().getNickname());
    }
}
