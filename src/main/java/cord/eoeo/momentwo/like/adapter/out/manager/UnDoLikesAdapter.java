package cord.eoeo.momentwo.like.adapter.out.manager;

import cord.eoeo.momentwo.elasticsearch.application.port.out.LikesElasticSearchManager;
import cord.eoeo.momentwo.like.advice.exception.NotFoundPhotoLikesException;
import cord.eoeo.momentwo.like.application.port.out.PhotoLikesGenericRepo;
import cord.eoeo.momentwo.like.application.port.out.PhotoLikesRedisGenericRepo;
import cord.eoeo.momentwo.like.application.port.out.PhotoLikesRedisKeyPort;
import cord.eoeo.momentwo.like.application.port.out.find.PhotoLikesFindByPhotoPort;
import cord.eoeo.momentwo.like.application.port.out.manager.UnDoLikesPort;
import cord.eoeo.momentwo.like.domain.PhotoLike;
import cord.eoeo.momentwo.photo.advice.exception.NotFoundPhotoException;
import cord.eoeo.momentwo.photo.application.port.out.PhotoGenericRepo;
import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UnDoLikesAdapter implements UnDoLikesPort {
    private final UserNicknameValidPort userNicknameValidPort;
    private final LikesElasticSearchManager likesElasticSearchManager;
    private final PhotoGenericRepo photoGenericRepo;
    private final PhotoLikesFindByPhotoPort photoLikesFindByPhotoPort;
    private final PhotoLikesGenericRepo photoLikesGenericRepo;
    private final PhotoLikesRedisGenericRepo photoLikesRedisGenericRepo;
    private final PhotoLikesRedisKeyPort photoLikesRedisKeyPort;

    @Override
    public void undoLikes(long photoId) {
        User user = userNicknameValidPort.authenticationValid();

        // 검색엔진에 좋아요를 누른상태면 삭제 진행
        if(likesElasticSearchManager.isLikes(user, photoId)) {
            Photo photo = photoGenericRepo.findById(photoId).orElseThrow(NotFoundPhotoException::new);
            PhotoLike photoLike = photoLikesFindByPhotoPort
                    .findByPhoto(photo).orElseThrow(NotFoundPhotoLikesException::new);

            photoLike.setCount(photoLike.getCount() - 1);

            photoLikesGenericRepo.save(photoLike);
            likesElasticSearchManager.deleteByLikes(photoId, user.getNickname());

            // 캐시에 값이 있으면 갱신
            String key = photoLikesRedisKeyPort.getKey(photoId);
            Object isPhotoLikes = photoLikesRedisGenericRepo.get(key);
            if(isPhotoLikes != null) {
                photoLikesRedisGenericRepo
                        .set(key, String.valueOf(Long.parseLong(String.valueOf(isPhotoLikes)) - 1L));
            }
        }
    }
}
