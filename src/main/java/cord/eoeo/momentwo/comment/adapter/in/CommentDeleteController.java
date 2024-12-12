package cord.eoeo.momentwo.comment.adapter.in;

import cord.eoeo.momentwo.comment.application.port.in.DeleteCommentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentDeleteController {
    private final DeleteCommentUseCase deleteCommentUseCase;

    @DeleteMapping("/albums/{albumId}/sub/photos/comments/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long albumId, @PathVariable Long commentId) {
        deleteCommentUseCase.deleteComment(albumId, commentId);
    }
}
