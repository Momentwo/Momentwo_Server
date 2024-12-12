package cord.eoeo.momentwo.comment.application.port.in;

public interface DeleteCommentUseCase {
    /**
     * 댓글 삭제
     * @param albumId
     * @param commentId
     * albumId : 앨범 아이디
     * commentId : 댓글 아이디
     */
    void deleteComment(Long albumId, Long commentId);
}
