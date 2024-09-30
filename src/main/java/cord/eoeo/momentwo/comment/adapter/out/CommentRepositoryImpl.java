package cord.eoeo.momentwo.comment.adapter.out;

import cord.eoeo.momentwo.comment.application.port.out.CommentJpaRepository;
import cord.eoeo.momentwo.comment.application.port.out.CommentRepository;
import cord.eoeo.momentwo.comment.domain.Comment;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
    private final CommentJpaRepository commentJpaRepository;

    @Override
    public void save(Comment comment) {
        commentJpaRepository.save(comment);
    }

    @Override
    public Optional<Comment> findByIdAndUser(long id, User user) {
        return commentJpaRepository.findByIdAndUser(id, user);
    }

    @Override
    public void deleteById(long id) {
        commentJpaRepository.deleteById(id);
    }
}
