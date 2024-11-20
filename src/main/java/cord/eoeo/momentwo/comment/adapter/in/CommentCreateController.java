package cord.eoeo.momentwo.comment.adapter.in;

import cord.eoeo.momentwo.comment.adapter.dto.in.CommentCreateRequestDto;
import cord.eoeo.momentwo.comment.application.port.in.CreateCommentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CommentCreateController {
    private final CreateCommentUseCase createCommentUseCase;

    @PostMapping("/photo/comments/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createComment(@RequestBody @Valid CommentCreateRequestDto commentCreateRequestDto) {
        createCommentUseCase.createComment(commentCreateRequestDto);
    }
}
