package cord.eoeo.momentwo.like.adapter.out.manager;

import cord.eoeo.momentwo.like.advice.exception.NotFoundPhotoLikesException;
import cord.eoeo.momentwo.like.application.port.out.find.PhotoLikesFindByPhotoPort;
import cord.eoeo.momentwo.like.application.port.out.manager.GetLikesPort;
import cord.eoeo.momentwo.like.domain.PhotoLike;
import cord.eoeo.momentwo.photo.advice.exception.NotFoundPhotoException;
import cord.eoeo.momentwo.photo.application.port.out.PhotoGenericRepo;
import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetLikesAdapter implements GetLikesPort {
    private final PhotoGenericRepo photoGenericRepo;
    private final PhotoLikesFindByPhotoPort photoLikesFindByPhotoPort;

    @Override
    public PhotoLike getLikes(long photoId) {
        Photo photo = photoGenericRepo.findById(photoId).orElseThrow(NotFoundPhotoException::new);

        return photoLikesFindByPhotoPort.findByPhoto(photo)
                .orElseThrow(NotFoundPhotoLikesException::new);
    }
}
