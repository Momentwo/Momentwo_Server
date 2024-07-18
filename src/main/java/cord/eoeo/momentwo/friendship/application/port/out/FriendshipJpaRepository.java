package cord.eoeo.momentwo.friendship.application.port.out;

import cord.eoeo.momentwo.friendship.domain.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipJpaRepository extends JpaRepository<Friendship, Long> {
}
