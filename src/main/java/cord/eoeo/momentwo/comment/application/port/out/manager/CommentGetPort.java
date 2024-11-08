package cord.eoeo.momentwo.comment.application.port.out.manager;

import cord.eoeo.momentwo.comment.adapter.dto.out.CommentListResponseDto;
import org.springframework.data.domain.Pageable;

public interface CommentGetPort {
    CommentListResponseDto commentGet(long PhotoId, Pageable pageable, long cursorId);
}
