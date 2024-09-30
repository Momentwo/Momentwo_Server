package cord.eoeo.momentwo.comment.adapter.in;

import cord.eoeo.momentwo.comment.adapter.dto.in.CommentCreateRequestDto;
import cord.eoeo.momentwo.comment.adapter.dto.in.CommentDeleteRequestDto;
import cord.eoeo.momentwo.comment.adapter.dto.in.CommentEditRequestDto;
import cord.eoeo.momentwo.comment.adapter.dto.in.CommentGetRequestDto;
import cord.eoeo.momentwo.comment.adapter.dto.out.CommentListResponseDto;
import cord.eoeo.momentwo.comment.application.port.in.CommentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/photo/comments")
public class CommentController {
    private final int PAGE_SIZE = 10;
    private final CommentUseCase commentUseCase;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public void createComment(@RequestBody @Valid CommentCreateRequestDto commentCreateRequestDto) {
        commentUseCase.createComment(commentCreateRequestDto);
    }

    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public void editComment(@RequestBody @Valid CommentEditRequestDto commentEditRequestDto) {
        commentUseCase.editComment(commentEditRequestDto);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@RequestBody @Valid CommentDeleteRequestDto commentDeleteRequestDto) {
        commentUseCase.deleteComment(commentDeleteRequestDto);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public CommentListResponseDto getComment(@RequestBody @Valid CommentGetRequestDto commentGetRequestDto,
                 @PageableDefault(size = PAGE_SIZE, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return commentUseCase.getComment(commentGetRequestDto, pageable);
    }
}
