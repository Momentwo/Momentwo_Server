package cord.eoeo.momentwo.comment.application.port.out.jpa;

import cord.eoeo.momentwo.comment.application.port.out.CommentGenericJpaRepo;
import cord.eoeo.momentwo.comment.domain.Comment;
import cord.eoeo.momentwo.user.domain.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CommentFindJpaRepo extends CommentGenericJpaRepo {
    @Query("SELECT c FROM Comment c WHERE c.id = :id AND c.user = :user")
    Optional<Comment> findByIdAndUser(long id, User user);
}
