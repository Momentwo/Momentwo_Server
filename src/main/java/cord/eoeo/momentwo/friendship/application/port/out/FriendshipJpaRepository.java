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
}
