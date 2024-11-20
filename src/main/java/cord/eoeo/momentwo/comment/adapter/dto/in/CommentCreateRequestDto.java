package cord.eoeo.momentwo.comment.adapter.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateRequestDto {
    @NotNull(message = "앨범 아이디 누락")
    private Long albumId;

    @NotNull(message = "포토 아이디 누락")
    private Long photoId;

    @NotBlank(message = "댓글 누락")
    private String comments;
}
