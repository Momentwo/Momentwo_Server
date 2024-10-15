package cord.eoeo.momentwo.comment.adapter.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentEditRequestDto {
    private long albumId;
    private long CommentId;
    private String editComments;
}
