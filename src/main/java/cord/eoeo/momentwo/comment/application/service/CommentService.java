package cord.eoeo.momentwo.comment.application.service;

import cord.eoeo.momentwo.comment.adapter.dto.in.CommentCreateRequestDto;
import cord.eoeo.momentwo.comment.adapter.dto.in.CommentDeleteRequestDto;
import cord.eoeo.momentwo.comment.adapter.dto.in.CommentEditRequestDto;
import cord.eoeo.momentwo.comment.adapter.dto.in.CommentGetRequestDto;
import cord.eoeo.momentwo.comment.adapter.dto.out.CommentListResponseDto;
import cord.eoeo.momentwo.comment.application.port.in.CommentUseCase;
import cord.eoeo.momentwo.comment.application.port.out.CommentManager;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class CommentService implements CommentUseCase {
    private final CommentManager commentManager;

    @Override
    @CheckAlbumAccessRules
    public void createComment(CommentCreateRequestDto commentCreateRequestDto) {
        commentManager.commentCreate(
                commentCreateRequestDto.getComments(),
                commentCreateRequestDto.getPhotoId()
        );
    }

    @Override
    @CheckAlbumAccessRules
    public void editComment(CommentEditRequestDto commentEditRequestDto) {
        commentManager.commentEdit(
                commentEditRequestDto.getEditComments(),
                commentEditRequestDto.getCommentId()
        );
    }

    @Override
    @CheckAlbumAccessRules
    public void deleteComment(CommentDeleteRequestDto commentDeleteRequestDto) {
        commentManager.commentDelete(commentDeleteRequestDto.getCommentId());
    }

    @Override
    @CheckAlbumAccessRules
    public CommentListResponseDto getComment(long albumId, long photoId, long cursor, Pageable pageable) {
        return commentManager.commentGet(
                photoId,
                pageable,
                cursor
        );
    }
}
