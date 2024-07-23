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
public class FriendshipReceiveListResponseDto {
    private List<FriendshipReceiveListDto> friendshipReceiveList;
    public FriendshipReceiveListResponseDto toDO(List<Friendship> friendshipReceiveList) {
        return new FriendshipReceiveListResponseDto(
                friendshipReceiveList.stream().map(friends -> new FriendshipReceiveListDto().toDo(friends))
                        .collect(Collectors.toList())
        );
    }
}
