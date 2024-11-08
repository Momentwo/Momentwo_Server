package cord.eoeo.momentwo.comment.application.service;

import cord.eoeo.momentwo.comment.adapter.dto.in.CommentCreateRequestDto;
import cord.eoeo.momentwo.comment.application.port.in.CreateCommentUseCase;
import cord.eoeo.momentwo.comment.application.port.out.manager.CommentCreatePort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentCreateService implements CreateCommentUseCase {
    private final CommentCreatePort commentCreatePort;

    @Override
    @CheckAlbumAccessRules
    @Transactional
    public void createComment(CommentCreateRequestDto commentCreateRequestDto) {
        commentCreatePort.commentCreate(
                commentCreateRequestDto.getComments(),
                commentCreateRequestDto.getPhotoId()
        );
    }
}
