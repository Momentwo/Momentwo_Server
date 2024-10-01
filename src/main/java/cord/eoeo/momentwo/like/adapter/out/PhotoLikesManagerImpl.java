package cord.eoeo.momentwo.like.adapter.out;

import cord.eoeo.momentwo.elasticsearch.application.port.out.LikesElasticSearchManager;
import cord.eoeo.momentwo.like.advice.exception.NotFoundPhotoLikesException;
import cord.eoeo.momentwo.like.application.port.out.PhotoLikesManager;
import cord.eoeo.momentwo.like.application.port.out.PhotoLikesRepository;
import cord.eoeo.momentwo.like.domain.PhotoLike;
import cord.eoeo.momentwo.photo.advice.exception.NotFoundPhotoException;
import cord.eoeo.momentwo.photo.application.port.out.PhotoRepository;
import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.application.port.out.UserRepository;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class PhotoLikesManagerImpl implements PhotoLikesManager {
    private final PhotoLikesRepository photoLikesRepository;
    private final PhotoRepository photoRepository;
    private final LikesElasticSearchManager likesElasticSearchManager;
    private final GetAuthentication getAuthentication;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void doLikes(long photoId) {
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);
        Photo photo = photoRepository.findById(photoId).orElseThrow(NotFoundPhotoException::new);

        // 검색엔진에 좋아요가 없다면 좋아요를 누를 수 있는 상태
        if(!likesElasticSearchManager.isLikes(user, photoId)) {
            photoLikesRepository.findByPhoto(photo).ifPresentOrElse(
                    photoLike -> {
                        // 값이 존재하는 경우 수행할 작업
                        photoLike.setCount(photoLike.getCount() + 1);
                        photoLikesRepository.save(photoLike);
                        likesElasticSearchManager.save(user, photo);
                    },
                    () -> {
                        // 값이 존재하지 않는 경우 수행할 작업
                        PhotoLike photoLike = new PhotoLike(photo);
                        photoLikesRepository.save(photoLike);
                        likesElasticSearchManager.save(user, photo);
                    });
        }
    }

    @Override
    @Transactional
    public void undoLikes(long photoId) {
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);
        
        // 검색엔진에 좋아요를 누른상태면 삭제 진행
        if(likesElasticSearchManager.isLikes(user, photoId)) {
            Photo photo = photoRepository.findById(photoId).orElseThrow(NotFoundPhotoException::new);
            PhotoLike photoLike = photoLikesRepository.findByPhoto(photo).orElseThrow(NotFoundPhotoLikesException::new);

            photoLike.setCount(photoLike.getCount() - 1);

            photoLikesRepository.save(photoLike);
            likesElasticSearchManager.deleteByLikes(photoId, user.getNickname());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PhotoLike getLikes(long photoId) {
        Photo photo = photoRepository.findById(photoId).orElseThrow(NotFoundPhotoException::new);

        return photoLikesRepository.findByPhoto(photo)
                .orElseThrow(NotFoundPhotoLikesException::new);
    }
}
