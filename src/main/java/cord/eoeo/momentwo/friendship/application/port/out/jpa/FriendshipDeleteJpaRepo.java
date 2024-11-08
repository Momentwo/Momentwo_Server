package cord.eoeo.momentwo.friendship.application.port.out.jpa;

import cord.eoeo.momentwo.friendship.application.port.out.FriendshipGenericJpaRepo;
import cord.eoeo.momentwo.user.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FriendshipDeleteJpaRepo extends FriendshipGenericJpaRepo {
    @Modifying
    @Query("DELETE Friendship f WHERE (f.fromUser.id = :fromUserId AND f.toUser.id = :toUserId) OR" +
            "(f.fromUser.id = :toUserId AND f.toUser.id = :fromUserId)")
    void deleteByFromUserAndToUser(long fromUserId, long toUserId);

    @Modifying
    @Query("DELETE Friendship f WHERE (f.toUser = :toUser AND f.fromUser = :fromUser AND f.accept = true) OR " +
            "(f.toUser = :fromUser AND f.fromUser = :toUser AND f.accept = true)")
    int deleteByToUserAndFromUser(User toUser, User fromUser);
}
