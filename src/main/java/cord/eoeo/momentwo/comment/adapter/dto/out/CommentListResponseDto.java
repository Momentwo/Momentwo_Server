package cord.eoeo.momentwo.comment.adapter.dto.out;

import cord.eoeo.momentwo.comment.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentListResponseDto {
    private List<CommentResponseDto> commentsList;
    private long page;
    private long size;
    private long totalPages;
    private long totalElements;
    private boolean hasNext;
    private boolean hasPrevious;

    public CommentListResponseDto toDo(Page<Comment> comments) {
        return new CommentListResponseDto(
                comments.stream().map(comment -> new CommentResponseDto().toDo(comment))
                .collect(Collectors.toList()),
                comments.getNumber(),
                comments.getSize(),
                comments.getTotalPages(),
                comments.getTotalElements(),
                comments.hasNext(),
                comments.hasPrevious()
        );
    }
}
