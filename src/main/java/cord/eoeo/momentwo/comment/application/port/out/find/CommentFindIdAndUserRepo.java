package cord.eoeo.momentwo.comment.application.port.out.find;

import cord.eoeo.momentwo.comment.domain.Comment;
import cord.eoeo.momentwo.user.domain.User;

import java.util.Optional;

public interface CommentFindIdAndUserRepo {
    Optional<Comment> findByIdAndUser(long id, User user);
}
