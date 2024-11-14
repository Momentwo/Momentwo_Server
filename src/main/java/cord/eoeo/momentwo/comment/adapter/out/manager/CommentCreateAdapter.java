package cord.eoeo.momentwo.comment.adapter.out.manager;

import cord.eoeo.momentwo.comment.application.port.out.CommentGenericRepo;
import cord.eoeo.momentwo.comment.application.port.out.manager.CommentCreatePort;
import cord.eoeo.momentwo.comment.domain.Comment;
import cord.eoeo.momentwo.photo.advice.exception.NotFoundPhotoException;
import cord.eoeo.momentwo.photo.application.port.out.PhotoGenericRepo;
import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentCreateAdapter implements CommentCreatePort {
    private final UserNicknameValidPort userNicknameValidPort;
    private final PhotoGenericRepo photoGenericRepo;
    private final CommentGenericRepo commentGenericRepo;

    @Override
    public void commentCreate(String comments, long photoId) {
        User user = userNicknameValidPort.authenticationValid();
        Photo photo = photoGenericRepo.findById(photoId).orElseThrow(NotFoundPhotoException::new);

        Comment comment = new Comment(comments, user, photo);
        commentGenericRepo.save(comment);
    }
}
