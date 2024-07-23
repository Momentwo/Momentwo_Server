package cord.eoeo.momentwo.friendship.adapter.in.dto;

import cord.eoeo.momentwo.friendship.domain.Friendship;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipSendListResponseDto {
    private List<FriendshipSendListDto> friendshipSendList;
    public FriendshipSendListResponseDto toDo(List<Friendship> friendships) {
        return new FriendshipSendListResponseDto(
                friendships.stream().map(friendship -> new FriendshipSendListDto().toDo(friendship))
                        .collect(Collectors.toList())
        );
    }
}
