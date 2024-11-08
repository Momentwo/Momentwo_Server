package cord.eoeo.momentwo.comment.adapter.out.manager;

import cord.eoeo.momentwo.comment.adapter.dto.out.CommentListResponseDto;
import cord.eoeo.momentwo.comment.application.port.out.CommentPageRepository;
import cord.eoeo.momentwo.comment.application.port.out.manager.CommentGetPort;
import cord.eoeo.momentwo.photo.advice.exception.NotFoundPhotoException;
import cord.eoeo.momentwo.photo.application.port.out.PhotoGenericRepo;
import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentGetAdapter implements CommentGetPort {
    private final PhotoGenericRepo photoGenericRepo;
    private final CommentPageRepository commentPageRepository;

    @Override
    public CommentListResponseDto commentGet(long photoId, Pageable pageable, long cursorId) {
        Photo photo = photoGenericRepo.findById(photoId).orElseThrow(NotFoundPhotoException::new);
        return new CommentListResponseDto()
                .toDo(commentPageRepository.getCommentByPhotoPaging(photo, pageable, cursorId));
    }
}
