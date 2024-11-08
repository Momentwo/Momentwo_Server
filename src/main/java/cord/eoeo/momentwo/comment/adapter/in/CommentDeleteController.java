package cord.eoeo.momentwo.comment.adapter.in;

import cord.eoeo.momentwo.comment.adapter.dto.in.CommentDeleteRequestDto;
import cord.eoeo.momentwo.comment.application.port.in.DeleteCommentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CommentDeleteController {
    private final DeleteCommentUseCase deleteCommentUseCase;

    @DeleteMapping("/photo/comments")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@RequestBody @Valid CommentDeleteRequestDto commentDeleteRequestDto) {
        deleteCommentUseCase.deleteComment(commentDeleteRequestDto);
    }
}
