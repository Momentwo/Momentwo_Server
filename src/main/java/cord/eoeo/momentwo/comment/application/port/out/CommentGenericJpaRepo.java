package cord.eoeo.momentwo.comment.application.port.out;

import cord.eoeo.momentwo.comment.domain.Comment;
import cord.eoeo.momentwo.global.application.port.out.GenericDefaultJpaRepo;

public interface CommentGenericJpaRepo extends GenericDefaultJpaRepo<Comment, Long> {
}
