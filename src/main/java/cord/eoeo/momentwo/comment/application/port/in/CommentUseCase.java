package cord.eoeo.momentwo.comment.application.port.in;

import cord.eoeo.momentwo.comment.adapter.dto.in.CommentCreateRequestDto;
import cord.eoeo.momentwo.comment.adapter.dto.in.CommentDeleteRequestDto;
import cord.eoeo.momentwo.comment.adapter.dto.in.CommentEditRequestDto;
import cord.eoeo.momentwo.comment.adapter.dto.in.CommentGetRequestDto;
import cord.eoeo.momentwo.comment.adapter.dto.out.CommentListResponseDto;
import org.springframework.data.domain.Pageable;

public interface CommentUseCase {
    void createComment(CommentCreateRequestDto commentCreateRequestDto);
    void editComment(CommentEditRequestDto commentEditRequestDto);
    void deleteComment(CommentDeleteRequestDto commentDeleteRequestDto);
    CommentListResponseDto getComment(long albumId, long photoId, long cursor, Pageable pageable);
}
