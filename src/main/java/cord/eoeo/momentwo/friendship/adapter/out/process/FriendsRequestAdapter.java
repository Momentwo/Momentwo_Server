package cord.eoeo.momentwo.friendship.adapter.out.process;

import cord.eoeo.momentwo.friendship.application.port.out.FriendshipGenericRepo;
import cord.eoeo.momentwo.friendship.application.port.out.process.FriendsRequestPort;
import cord.eoeo.momentwo.friendship.domain.Friendship;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FriendsRequestAdapter implements FriendsRequestPort {
    private final FriendshipGenericRepo friendshipGenericRepo;

    @Override
    public void friendsRequest(User from, User to, boolean type) {
        Friendship newFriendship = new Friendship(
                from,
                to,
                type
        );
        friendshipGenericRepo.save(newFriendship);
    }
}
