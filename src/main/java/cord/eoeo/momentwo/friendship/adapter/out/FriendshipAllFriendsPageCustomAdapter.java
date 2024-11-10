package cord.eoeo.momentwo.friendship.adapter.out;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import cord.eoeo.momentwo.comment.domain.QComment;
import cord.eoeo.momentwo.config.page.CursorPage;
import cord.eoeo.momentwo.friendship.application.port.out.FriendshipAllFriendsPageCustomPort;
import cord.eoeo.momentwo.friendship.domain.Friendship;
import cord.eoeo.momentwo.friendship.domain.QFriendship;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FriendshipAllFriendsPageCustomAdapter implements FriendshipAllFriendsPageCustomPort {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public CursorPage<Friendship> allFriendsCustom(User owner, Pageable pageable, long cursorId) {
        // 셀프 조인
        QFriendship qF1 = QFriendship.friendship;
        QFriendship qF2 = new QFriendship("qF2");

        List<Friendship> friendships = jpaQueryFactory
                .select(qF2)
                .from(qF1)
                .join(qF2).on(qF1.fromUser.eq(qF2.toUser).and(qF1.toUser.eq(qF2.fromUser)))
                .where(qF2.toUser.eq(owner).and(qF1.accept.isTrue()).and(qF2.accept.isTrue())
                        .and(cursor(cursorId, qF1)))
                .fetch();

        return new CursorPage<>(
                friendships,
                friendships.isEmpty() ? null : friendships.get(friendships.size()-1).getId()
        );
    }

    private BooleanExpression cursor(Long cursorId, QFriendship friendship) {
        if(cursorId == null) {
            return null;
        }
        return friendship.id.gt(cursorId);
    }
}
