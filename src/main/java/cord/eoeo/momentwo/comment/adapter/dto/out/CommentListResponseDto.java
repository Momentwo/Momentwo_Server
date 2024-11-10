package cord.eoeo.momentwo.comment.adapter.dto.out;

import cord.eoeo.momentwo.comment.domain.Comment;
import cord.eoeo.momentwo.config.page.CursorPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentListResponseDto {
    private List<CommentResponseDto> commentsList;
    private Object nextCursor;

    public CommentListResponseDto toDo(CursorPage<Comment> comments) {
        return new CommentListResponseDto(
                comments.stream().map(comment -> new CommentResponseDto().toDo(comment))
                .collect(Collectors.toList()),
                comments.getNextCursor()
        );
    }
}
