package cord.eoeo.momentwo.comment.application.service;

import cord.eoeo.momentwo.comment.adapter.dto.in.CommentDeleteRequestDto;
import cord.eoeo.momentwo.comment.application.port.in.DeleteCommentUseCase;
import cord.eoeo.momentwo.comment.application.port.out.manager.CommentDeletePort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentDeleteService implements DeleteCommentUseCase {
    private final CommentDeletePort commentDeletePort;

    @Override
    @CheckAlbumAccessRules
    @Transactional
    public void deleteComment(CommentDeleteRequestDto commentDeleteRequestDto) {
        commentDeletePort.commentDelete(commentDeleteRequestDto.getCommentId());
    }
}
