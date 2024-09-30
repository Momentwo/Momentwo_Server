package cord.eoeo.momentwo.comment.application.port.out;

import cord.eoeo.momentwo.comment.domain.Comment;
import cord.eoeo.momentwo.user.domain.User;

import java.util.Optional;

public interface CommentRepository {
    void save(Comment comment);

    Optional<Comment> findByIdAndUser(long id, User user);

    void deleteById(long id);
}
