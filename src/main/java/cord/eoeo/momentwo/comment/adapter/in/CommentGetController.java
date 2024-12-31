package cord.eoeo.momentwo.comment.adapter.in;

import cord.eoeo.momentwo.comment.adapter.dto.out.CommentListResponseDto;
import cord.eoeo.momentwo.comment.application.port.in.GetCommentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentGetController {
    private final GetCommentUseCase getCommentUseCase;

    @GetMapping("/albums/{albumId}/sub/photos/{photoId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public CommentListResponseDto getComment(
            @PathVariable long albumId,
            @PathVariable long photoId,
            @RequestParam int size,
            @RequestParam(required = false, defaultValue = "0") long cursor
    ) {
        return getCommentUseCase.getComment(albumId, photoId, size, cursor);
    }
}
