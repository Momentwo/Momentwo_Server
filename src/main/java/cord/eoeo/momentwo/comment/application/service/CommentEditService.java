package cord.eoeo.momentwo.comment.application.service;

import cord.eoeo.momentwo.comment.adapter.dto.in.CommentEditRequestDto;
import cord.eoeo.momentwo.comment.application.port.in.EditCommentUseCase;
import cord.eoeo.momentwo.comment.application.port.out.manager.CommentEditPort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentEditService implements EditCommentUseCase {
    private final CommentEditPort commentEditPort;

    @Override
    @CheckAlbumAccessRules
    @Transactional
    public void editComment(CommentEditRequestDto commentEditRequestDto) {
        commentEditPort.commentEdit(
                commentEditRequestDto.getEditComments(),
                commentEditRequestDto.getCommentId()
        );
    }
}
