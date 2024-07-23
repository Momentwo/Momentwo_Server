package cord.eoeo.momentwo.friendship.adapter.out;

import cord.eoeo.momentwo.friendship.application.port.out.FriendshipJpaRepository;
import cord.eoeo.momentwo.friendship.application.port.out.FriendshipRepository;
import cord.eoeo.momentwo.friendship.domain.Friendship;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class FriendshipRepositoryImpl implements FriendshipRepository {
    private final FriendshipJpaRepository friendshipJpaRepository;

    @Override
    public void save(Friendship friendship) {
        friendshipJpaRepository.save(friendship);
    }

    @Override
    public Optional<Friendship> findByToUserAndFromUserAndAccept(User toUser, User fromUser, boolean accept) {
        return friendshipJpaRepository.findByToUserAndFromUserAndAccept(toUser.getId(), fromUser.getId(), accept);
    }

    @Override
    public void deleteByFromUserAndToUser(User fromUser, User toUser) {
        friendshipJpaRepository.deleteByFromUserAndToUser(fromUser.getId(), toUser.getId());
    }

    @Override
    public Friendship findByToUserAndFromUser(User toUser, User fromUser) {
        return friendshipJpaRepository.findByToUserAndFromUser(toUser, fromUser);
    }

    @Override
    public Optional<Friendship> findBySelfJoin(User fromUser, User toUser) {
        return friendshipJpaRepository.findBySelfJoin(fromUser, toUser);
    }

    @Override
    public List<Friendship> findAllFriendsByUser(User owner) {
        return friendshipJpaRepository.findAllFriendsByUser(owner);
    }

    @Override
    public List<Friendship> findSendFriendsByUser(User owner) {
        return friendshipJpaRepository.findSendFriendsByUser(owner);
    }

    @Override
    public List<Friendship> findReceiveFriendsByUser(User owner) {
        return friendshipJpaRepository.findReceiveFriendsByUser(owner);
    }
}
