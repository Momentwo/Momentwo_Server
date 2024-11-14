package cord.eoeo.momentwo.comment.adapter.out.manager;

import cord.eoeo.momentwo.comment.advice.exception.NotCommentAccessException;
import cord.eoeo.momentwo.comment.application.port.out.CommentGenericRepo;
import cord.eoeo.momentwo.comment.application.port.out.find.CommentFindIdAndUserRepo;
import cord.eoeo.momentwo.comment.application.port.out.manager.CommentEditPort;
import cord.eoeo.momentwo.comment.domain.Comment;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentEditAdapter implements CommentEditPort {
    private final UserNicknameValidPort userNicknameValidPort;
    private final CommentFindIdAndUserRepo commentFindIdAndUserRepo;
    private final CommentGenericRepo commentGenericRepo;

    @Override
    public void commentEdit(String editComments, long commentId) {
        User user = userNicknameValidPort.authenticationValid();
        Comment comment = commentFindIdAndUserRepo.findByIdAndUser(commentId, user)
                .orElseThrow(NotCommentAccessException::new);
        comment.setComments(editComments);
        commentGenericRepo.save(comment);
    }
}
