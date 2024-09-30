package cord.eoeo.momentwo.comment.adapter.out;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import cord.eoeo.momentwo.comment.application.port.out.CommentPageRepository;
import cord.eoeo.momentwo.comment.domain.Comment;
import cord.eoeo.momentwo.comment.domain.QComment;
import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentPageRepositoryImpl implements CommentPageRepository {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Page<Comment> getCommentByPhotoPaging(Photo photo, Pageable pageable, long cursorId) {
        QComment comment = QComment.comment;

        List<Comment> comments = jpaQueryFactory
                .select(comment)
                .from(comment)
                .where(comment.photo.eq(photo).and(cursor(cursorId, comment)))
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(comments);
    }

    private BooleanExpression cursor(Long cursorId, QComment comment) {
        if(cursorId == null) {
            return null;
        }
        return comment.id.gt(cursorId);
    }
}
