package cord.eoeo.momentwo.friendship.application.port.out;

import cord.eoeo.momentwo.friendship.domain.Friendship;
import cord.eoeo.momentwo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FriendshipJpaRepository extends JpaRepository<Friendship, Long> {
    @Query("SELECT f FROM Friendship f WHERE f.fromUser.id = :toUserId AND" +
            " f.toUser.id = :fromUserId AND f.accept = :accept")
    Optional<Friendship> findByToUserAndFromUserAndAccept(long toUserId, long fromUserId, boolean accept);

    @Modifying
    @Query("DELETE Friendship f WHERE (f.fromUser.id = :fromUserId AND f.toUser.id = :toUserId) OR" +
            "(f.fromUser.id = :toUserId AND f.toUser.id = :fromUserId)")
    void deleteByFromUserAndToUser(long fromUserId, long toUserId);

    @Query("SELECT f FROM Friendship f WHERE f.fromUser = :toUser AND f.toUser = :fromUser")
    Friendship findByToUserAndFromUser(User toUser, User fromUser);

    @Query("SELECT f1 FROM Friendship f1 JOIN Friendship f2 ON f1.fromUser = f2.toUser " +
            "AND f1.toUser = f2.fromUser" +
            " WHERE f1.fromUser = :fromUser AND f1.toUser = :toUser AND f1.accept = true AND f2.accept = false")
    Optional<Friendship> findBySelfJoin(User fromUser, User toUser);
}
