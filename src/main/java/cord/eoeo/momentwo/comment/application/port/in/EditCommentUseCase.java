package cord.eoeo.momentwo.comment.application.port.in;

import cord.eoeo.momentwo.comment.adapter.dto.in.CommentEditRequestDto;

public interface EditCommentUseCase {
    /**
     * 댓글 수정
     * @param commentEditRequestDto
     * albumId : 앨범 아이디
     * commentId : 댓글 아이디
     * editComments : 수정할 댓글
     */
    void editComment(CommentEditRequestDto commentEditRequestDto);
}
