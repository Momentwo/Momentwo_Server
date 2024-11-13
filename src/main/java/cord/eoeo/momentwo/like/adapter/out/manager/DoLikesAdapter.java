package cord.eoeo.momentwo.like.adapter.out.manager;

import cord.eoeo.momentwo.elasticsearch.application.port.out.LikesElasticSearchManager;
import cord.eoeo.momentwo.like.application.port.out.PhotoLikesGenericRepo;
import cord.eoeo.momentwo.like.application.port.out.PhotoLikesRedisGenericRepo;
import cord.eoeo.momentwo.like.application.port.out.PhotoLikesRedisKeyPort;
import cord.eoeo.momentwo.like.application.port.out.find.PhotoLikesFindByPhotoPort;
import cord.eoeo.momentwo.like.application.port.out.manager.DoLikesPort;
import cord.eoeo.momentwo.like.domain.PhotoLike;
import cord.eoeo.momentwo.photo.advice.exception.NotFoundPhotoException;
import cord.eoeo.momentwo.photo.application.port.out.PhotoGenericRepo;
import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.application.port.out.find.UserFindNicknameRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DoLikesAdapter implements DoLikesPort {
    private final UserFindNicknameRepo userFindNicknameRepo;
    private final GetAuthentication getAuthentication;
    private final PhotoGenericRepo photoGenericRepo;
    private final LikesElasticSearchManager likesElasticSearchManager;
    private final PhotoLikesFindByPhotoPort photoLikesFindByPhotoPort;
    private final PhotoLikesGenericRepo photoLikesGenericRepo;
    private final PhotoLikesRedisGenericRepo photoLikesRedisGenericRepo;
    private final PhotoLikesRedisKeyPort photoLikesRedisKeyPort;

    @Override
    public void doLikes(long photoId) {
        User user = userFindNicknameRepo.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);
        Photo photo = photoGenericRepo.findById(photoId).orElseThrow(NotFoundPhotoException::new);


        // 검색엔진에 좋아요가 없다면 좋아요를 누를 수 있는 상태
        if(!likesElasticSearchManager.isLikes(user, photoId)) {
            photoLikesFindByPhotoPort.findByPhoto(photo).ifPresentOrElse(
                    photoLike -> {
                        // 값이 존재하는 경우 수행할 작업
                        photoLike.setCount(photoLike.getCount() + 1);
                        photoLikesGenericRepo.save(photoLike);
                        likesElasticSearchManager.save(user, photo);

                        // 캐시에 값이 있으면 갱신
                        String key = photoLikesRedisKeyPort.getKey(photoId);
                        Object isPhotoLikes = photoLikesRedisGenericRepo.get(key);
                        if(isPhotoLikes != null) {
                            photoLikesRedisGenericRepo
                                    .set(key, String.valueOf(Long.parseLong(String.valueOf(isPhotoLikes)) + 1L));
                        }
                    },
                    () -> {
                        // 값이 존재하지 않는 경우 수행할 작업
                        PhotoLike photoLike = new PhotoLike(photo);
                        photoLikesGenericRepo.save(photoLike);
                        likesElasticSearchManager.save(user, photo);
                    }
            );
        }
    }
}
