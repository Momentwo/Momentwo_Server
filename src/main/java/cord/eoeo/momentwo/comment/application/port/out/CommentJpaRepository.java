package cord.eoeo.momentwo.comment.application.port.out;

import cord.eoeo.momentwo.comment.domain.Comment;
import cord.eoeo.momentwo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.id = :id AND c.user = :user")
    Optional<Comment> findByIdAndUser(long id, User user);
}
