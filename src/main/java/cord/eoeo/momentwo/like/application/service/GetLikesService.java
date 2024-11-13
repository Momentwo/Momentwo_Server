package cord.eoeo.momentwo.like.application.service;

import cord.eoeo.momentwo.like.adapter.dto.out.PhotoLikesResponseDto;
import cord.eoeo.momentwo.like.application.port.in.GetLikesUseCase;
import cord.eoeo.momentwo.like.application.port.out.PhotoLikesRedisGenericRepo;
import cord.eoeo.momentwo.like.application.port.out.PhotoLikesRedisKeyPort;
import cord.eoeo.momentwo.like.application.port.out.manager.GetLikesPort;
import cord.eoeo.momentwo.like.domain.PhotoLike;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetLikesService implements GetLikesUseCase {
    private final GetLikesPort getLikesPort;
    private final PhotoLikesRedisGenericRepo photoLikesRedisGenericRepo;
    private final PhotoLikesRedisKeyPort photoLikesRedisKeyPort;

    @Override
    @CheckAlbumAccessRules
    @Transactional(readOnly = true)
    public PhotoLikesResponseDto getLikes(long albumId, long photoId) {
        String key = photoLikesRedisKeyPort.getKey(photoId);
        Object isGetRedis = photoLikesRedisGenericRepo.get(key);

        // 레디스 먼저 조회 후 리턴
        if(isGetRedis != null) {
            return new PhotoLikesResponseDto(Long.parseLong(String.valueOf(isGetRedis)));
        }

        // 레디스에 정보가 없다면 DB 조회
        PhotoLike photoLike = getLikesPort.getLikes(photoId);

        // 레디스에 업로드
        photoLikesRedisGenericRepo.set(key, String.valueOf(photoLike.getCount()));
        photoLikesRedisGenericRepo.expire(key, 10L);

        // 반환
        return new PhotoLikesResponseDto()
                .toDo(photoLike);
    }
}
