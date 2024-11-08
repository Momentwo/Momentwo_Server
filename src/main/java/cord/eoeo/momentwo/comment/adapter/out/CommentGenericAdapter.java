package cord.eoeo.momentwo.comment.adapter.out;

import cord.eoeo.momentwo.comment.application.port.out.CommentGenericJpaRepo;
import cord.eoeo.momentwo.comment.application.port.out.CommentGenericRepo;
import cord.eoeo.momentwo.comment.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentGenericAdapter implements CommentGenericRepo {
    private final CommentGenericJpaRepo commentGenericJpaRepo;

    @Override
    public void save(Comment entity) {
        commentGenericJpaRepo.save(entity);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentGenericJpaRepo.findById(id);
    }

    @Override
    public List<Comment> findAll() {
        return commentGenericJpaRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        commentGenericJpaRepo.deleteById(id);
    }
}
