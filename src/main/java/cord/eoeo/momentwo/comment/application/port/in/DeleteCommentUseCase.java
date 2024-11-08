package cord.eoeo.momentwo.comment.application.port.in;

import cord.eoeo.momentwo.comment.adapter.dto.in.CommentDeleteRequestDto;

public interface DeleteCommentUseCase {
    /**
     * 댓글 삭제
     * @param commentDeleteRequestDto
     * albumId : 앨범 아이디
     * commentId : 댓글 아이디
     */
    void deleteComment(CommentDeleteRequestDto commentDeleteRequestDto);
}
