package cord.eoeo.momentwo.comment.adapter.in;

import cord.eoeo.momentwo.comment.adapter.dto.in.CommentEditRequestDto;
import cord.eoeo.momentwo.comment.application.port.in.EditCommentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CommentEditController {
    private final EditCommentUseCase editCommentUseCase;
    @PutMapping("/photo/comments/edit")
    @ResponseStatus(HttpStatus.OK)
    public void editComment(@RequestBody @Valid CommentEditRequestDto commentEditRequestDto) {
        editCommentUseCase.editComment(commentEditRequestDto);
    }
}
