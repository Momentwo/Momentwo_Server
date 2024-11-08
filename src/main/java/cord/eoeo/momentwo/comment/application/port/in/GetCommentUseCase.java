package cord.eoeo.momentwo.comment.application.port.in;

import cord.eoeo.momentwo.comment.adapter.dto.out.CommentListResponseDto;

public interface GetCommentUseCase {
    /**
     *
     * @param albumId : 앨범 아이디
     * @param photoId : 사진 아이디
     * @param size : 크기
     * @param cursor : 커서
     * @return : 댓글 리스트
     */
    CommentListResponseDto getComment(long albumId, long photoId, int size ,long cursor);
}
