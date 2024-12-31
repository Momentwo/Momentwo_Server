package cord.eoeo.momentwo.friendship.adapter.dto;

import cord.eoeo.momentwo.friendship.domain.Friendship;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipAllListDto {
    private Long userId;
    private String nickname;
    private String userProfileImage;

    public FriendshipAllListDto toDo(Friendship friendship) {
        return new FriendshipAllListDto(
                friendship.getFromUser().getId(),
                friendship.getFromUser().getNickname(),
                friendship.getFromUser().getUserProfileImage()
        );
    }
}
