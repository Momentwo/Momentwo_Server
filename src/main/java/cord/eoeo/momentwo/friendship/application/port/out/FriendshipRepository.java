package cord.eoeo.momentwo.friendship.application.port.out;

import cord.eoeo.momentwo.friendship.domain.Friendship;
import cord.eoeo.momentwo.user.domain.User;

import java.util.Optional;

public interface FriendshipRepository {
    void save(Friendship friendship);

    // 친구 수락 대기 중인 상태의 테이블 중 해당 하는 값 찾기
    Optional<Friendship> findByToUserAndFromUserAndAccept(User toUser, User fromUser, boolean accept);

    // 유저 정보로 테이블 삭제
    void deleteByFromUserAndToUser(User fromUser, User toUser);

    Friendship findByToUserAndFromUser(User toUser, User fromUser);
}
