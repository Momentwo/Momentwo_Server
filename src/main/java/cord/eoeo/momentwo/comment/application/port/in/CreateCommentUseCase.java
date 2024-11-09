package cord.eoeo.momentwo.comment.application.port.in;

import cord.eoeo.momentwo.comment.adapter.dto.in.CommentCreateRequestDto;

public interface CreateCommentUseCase {
    /**
     * 댓글 생성
     * @param commentCreateRequestDto
     * albumId : 앨범 아이디
     * photoId : 사진 아이디
     * Comments : 댓글
     */
    void createComment(CommentCreateRequestDto commentCreateRequestDto);
}
