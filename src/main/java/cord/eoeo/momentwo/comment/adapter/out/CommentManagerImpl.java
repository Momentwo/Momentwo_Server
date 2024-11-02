package cord.eoeo.momentwo.comment.adapter.out;

import cord.eoeo.momentwo.comment.adapter.dto.out.CommentListResponseDto;
import cord.eoeo.momentwo.comment.advice.exception.NotCommentAccessException;
import cord.eoeo.momentwo.comment.application.port.out.CommentManager;
import cord.eoeo.momentwo.comment.application.port.out.CommentPageRepository;
import cord.eoeo.momentwo.comment.application.port.out.CommentRepository;
import cord.eoeo.momentwo.comment.domain.Comment;
import cord.eoeo.momentwo.photo.advice.exception.NotFoundPhotoException;
import cord.eoeo.momentwo.photo.application.port.out.PhotoRepository;
import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.application.port.out.find.UserFindNicknameRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class CommentManagerImpl implements CommentManager {
    private final CommentRepository commentRepository;
    private final GetAuthentication getAuthentication;
    private final UserFindNicknameRepo userFindNicknameRepo;
    private final PhotoRepository photoRepository;
    private final CommentPageRepository commentPageRepository;

    @Override
    @Transactional
    public void commentCreate(String comments, long photoId) {
        User user = userFindNicknameRepo.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);
        Photo photo = photoRepository.findById(photoId).orElseThrow(NotFoundPhotoException::new);

        Comment comment = new Comment(comments, user, photo);
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void commentEdit(String editComments, long commentId) {
        User user = userFindNicknameRepo.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);
        Comment comment = commentRepository.findByIdAndUser(commentId, user)
                .orElseThrow(NotCommentAccessException::new);
        comment.setComments(editComments);
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void commentDelete(long commentId) {
        User user = userFindNicknameRepo.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);
        commentRepository.findByIdAndUser(commentId, user).orElseThrow(NotCommentAccessException::new);
        commentRepository.deleteById(commentId);
    }

    @Override
    @Transactional(readOnly = true)
    public CommentListResponseDto commentGet(long photoId, Pageable pageable, long cursorId) {
        Photo photo = photoRepository.findById(photoId).orElseThrow(NotFoundPhotoException::new);
        return new CommentListResponseDto()
                .toDo(commentPageRepository.getCommentByPhotoPaging(photo, pageable, cursorId));
    }
}
