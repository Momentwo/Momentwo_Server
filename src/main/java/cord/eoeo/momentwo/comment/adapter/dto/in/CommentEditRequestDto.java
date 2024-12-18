package cord.eoeo.momentwo.comment.adapter.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentEditRequestDto {
    @NotNull(message = "앨범 아이디 누락")
    private Long albumId;

    @NotNull(message = "댓글 아이디 누락")
    private Long commentId;

    @NotBlank(message = "수정 댓글 누락")
    private String editComments;
}
