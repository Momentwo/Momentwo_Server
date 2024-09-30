package cord.eoeo.momentwo.comment.adapter.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDeleteRequestDto {
    private long albumId;
    private long commentId;
}
