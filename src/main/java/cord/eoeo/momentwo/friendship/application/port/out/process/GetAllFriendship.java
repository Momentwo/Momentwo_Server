package cord.eoeo.momentwo.friendship.application.port.out.process;

import cord.eoeo.momentwo.friendship.adapter.dto.FriendshipAllListResponseDto;
import cord.eoeo.momentwo.user.domain.User;
import org.springframework.data.domain.Pageable;

public interface GetAllFriendship {
    FriendshipAllListResponseDto getAllFriendship(User owner, Pageable pageable, long cursor);
}
