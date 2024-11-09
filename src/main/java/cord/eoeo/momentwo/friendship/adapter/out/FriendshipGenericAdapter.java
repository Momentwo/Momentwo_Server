package cord.eoeo.momentwo.friendship.adapter.out;

import cord.eoeo.momentwo.friendship.application.port.out.FriendshipGenericJpaRepo;
import cord.eoeo.momentwo.friendship.application.port.out.FriendshipGenericRepo;
import cord.eoeo.momentwo.friendship.domain.Friendship;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FriendshipGenericAdapter implements FriendshipGenericRepo {
    private final FriendshipGenericJpaRepo friendshipGenericJpaRepo;

    @Override
    public void save(Friendship entity) {
        friendshipGenericJpaRepo.save(entity);
    }

    @Override
    public Optional<Friendship> findById(Long id) {
        return friendshipGenericJpaRepo.findById(id);
    }

    @Override
    public List<Friendship> findAll() {
        return friendshipGenericJpaRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        friendshipGenericJpaRepo.deleteById(id);
    }
}
