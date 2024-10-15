package cord.eoeo.momentwo.comment.application.port.out;

import cord.eoeo.momentwo.comment.adapter.dto.out.CommentListResponseDto;
import org.springframework.data.domain.Pageable;

public interface CommentManager {
    void commentCreate(String comments, long photoId);
    void commentEdit(String editComments, long commentId);
    void commentDelete(long commentId);
    CommentListResponseDto commentGet(long PhotoId, Pageable pageable, long cursorId);
}
