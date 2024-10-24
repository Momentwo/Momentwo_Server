package cord.eoeo.momentwo.comment.adapter.dto.out;

import cord.eoeo.momentwo.comment.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {
    private long id;
    private String nickname;
    private String userProfileImage;
    private String comment;
    private LocalDate date;

    public CommentResponseDto toDo(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getUser().getNickname(),
                comment.getUser().getUserProfileImage(),
                comment.getComments(),
                comment.getDate()
        );
    }
}
