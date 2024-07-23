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
public class FriendshipAllListResponseDto {
    private List<FriendshipAllListDto> friendshipAllListDtoList;
    public FriendshipAllListResponseDto toDo(List<Friendship> friendships) {
        return new FriendshipAllListResponseDto(
                friendships.stream().map(friends -> new FriendshipAllListDto().toDo(friends))
                        .collect(Collectors.toList())
        );
    }
}
