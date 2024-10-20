package cord.eoeo.momentwo.friendship.adapter.in.dto;

import cord.eoeo.momentwo.friendship.domain.Friendship;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipSendListDto {
    private String nickname;
    private String userProfileImage;

    public FriendshipSendListDto toDo(Friendship friendship) {
        return new FriendshipSendListDto(
                friendship.getToUser().getNickname(),
                friendship.getToUser().getUserProfileImage()
        );
    }
}
