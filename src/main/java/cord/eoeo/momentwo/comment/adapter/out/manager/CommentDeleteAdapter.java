package cord.eoeo.momentwo.comment.adapter.out.manager;

import cord.eoeo.momentwo.comment.advice.exception.NotCommentAccessException;
import cord.eoeo.momentwo.comment.application.port.out.CommentGenericRepo;
import cord.eoeo.momentwo.comment.application.port.out.find.CommentFindIdAndUserRepo;
import cord.eoeo.momentwo.comment.application.port.out.manager.CommentDeletePort;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.application.port.out.find.UserFindNicknameRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentDeleteAdapter implements CommentDeletePort {
    private final UserFindNicknameRepo userFindNicknameRepo;
    private final GetAuthentication getAuthentication;
    private final CommentFindIdAndUserRepo commentFindIdAndUserRepo;
    private final CommentGenericRepo commentGenericRepo;

    @Override
    public void commentDelete(long commentId) {
        User user = userFindNicknameRepo.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);
        commentFindIdAndUserRepo.findByIdAndUser(commentId, user).orElseThrow(NotCommentAccessException::new);
        commentGenericRepo.deleteById(commentId);
    }
}
