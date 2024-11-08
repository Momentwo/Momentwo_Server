package cord.eoeo.momentwo.comment.adapter.out.find;

import cord.eoeo.momentwo.comment.application.port.out.find.CommentFindIdAndUserRepo;
import cord.eoeo.momentwo.comment.application.port.out.jpa.CommentFindJpaRepo;
import cord.eoeo.momentwo.comment.domain.Comment;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentFindIdAndUserAdapter implements CommentFindIdAndUserRepo {
    private final CommentFindJpaRepo commentFindJpaRepo;

    @Override
    public Optional<Comment> findByIdAndUser(long id, User user) {
        return commentFindJpaRepo.findByIdAndUser(id, user);
    }
}
