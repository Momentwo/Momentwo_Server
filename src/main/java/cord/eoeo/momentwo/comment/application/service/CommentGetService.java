package cord.eoeo.momentwo.comment.application.service;

import cord.eoeo.momentwo.comment.adapter.dto.out.CommentListResponseDto;
import cord.eoeo.momentwo.comment.application.port.in.GetCommentUseCase;
import cord.eoeo.momentwo.comment.application.port.out.manager.CommentGetPort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentGetService implements GetCommentUseCase {
    private final CommentGetPort commentGetPort;

    @Override
    @CheckAlbumAccessRules
    @Transactional(readOnly = true)
    public CommentListResponseDto getComment(long albumId, long photoId, int size, long cursor) {
        return commentGetPort.commentGet(
                photoId,
                PageRequest.of((int) (cursor / size), size),
                cursor
        );
    }
}
