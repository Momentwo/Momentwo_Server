package cord.eoeo.momentwo.friendship.adapter.dto;

import cord.eoeo.momentwo.config.page.CursorPage;
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
    private Object nextCursor;

    public FriendshipAllListResponseDto toDo(CursorPage<Friendship> friendships) {
        return new FriendshipAllListResponseDto(
                friendships.stream().map(friends -> new FriendshipAllListDto().toDo(friends))
                        .collect(Collectors.toList()),
                friendships.getNextCursor()
        );
    }
}
