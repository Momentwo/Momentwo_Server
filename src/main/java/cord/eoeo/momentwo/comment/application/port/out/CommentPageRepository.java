package cord.eoeo.momentwo.comment.application.port.out;

import cord.eoeo.momentwo.comment.domain.Comment;
import cord.eoeo.momentwo.photo.domain.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentPageRepository {
    Page<Comment> getCommentByPhotoPaging(Photo photo, Pageable pageable, long cursorId);
}
