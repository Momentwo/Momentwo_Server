package cord.eoeo.momentwo.tag.adapter.out.photo.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import cord.eoeo.momentwo.description.adapter.dto.out.DescriptionResponseDto;
import cord.eoeo.momentwo.description.application.port.out.DescriptionRedisGenericRepo;
import cord.eoeo.momentwo.description.application.port.out.DescriptionRedisKeyPort;
import cord.eoeo.momentwo.description.application.port.out.manager.DescriptionGetPhotoPort;
import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.tag.advice.exception.NotFoundTagException;
import cord.eoeo.momentwo.tag.advice.exception.TagDuplicateException;
import cord.eoeo.momentwo.tag.advice.exception.TagExceedException;
import cord.eoeo.momentwo.tag.application.port.out.album.AlbumTagGenericRepo;
import cord.eoeo.momentwo.tag.application.port.out.photo.PhotoTagGenericRepo;
import cord.eoeo.momentwo.tag.application.port.out.photo.jpa.PhotoTagGetPhotoIdAndAlbumTagIdRepo;
import cord.eoeo.momentwo.tag.application.port.out.photo.manager.PhotoTagAddPort;
import cord.eoeo.momentwo.tag.application.port.out.photo.manager.PhotoTagCountPort;
import cord.eoeo.momentwo.tag.application.port.out.photo.manager.PhotoTagGetPort;
import cord.eoeo.momentwo.tag.domain.AlbumTag;
import cord.eoeo.momentwo.tag.domain.PhotoTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhotoTagAddAdapter implements PhotoTagAddPort {
    private final DescriptionGetPhotoPort descriptionGetPhotoPort;
    private final PhotoTagGenericRepo photoTagGenericRepo;
    private final AlbumTagGenericRepo albumTagGenericRepo;
    private final PhotoTagCountPort photoTagCountPort;
    private final PhotoTagGetPhotoIdAndAlbumTagIdRepo photoTagGetPhotoIdAndAlbumTagIdRepo;
    private final DescriptionRedisGenericRepo descriptionRedisGenericRepo;
    private final DescriptionRedisKeyPort descriptionRedisKeyPort;
    private final ObjectMapper objectMapper;
    private final PhotoTagGetPort photoTagGetPort;

    @Override
    public void PhotoTagAdd(long albumId, long photoId, long albumTagId) {
        // 20개 확인
        if(photoTagCountPort.photoTagCount(photoId) >= 20) {
            throw new TagExceedException();
        }

        photoTagGetPhotoIdAndAlbumTagIdRepo.getPhotoIdAndAlbumTagId(photoId, albumTagId).ifPresentOrElse(
                photoTag -> {
                    throw new TagDuplicateException();
                },
                () -> {
                    // 설명(작성자) 확인
                    Photo photo = descriptionGetPhotoPort.getPhoto(photoId);

                    // 태그정보 확인
                    AlbumTag albumTag = albumTagGenericRepo.findById(albumTagId).orElseThrow(NotFoundTagException::new);

                    PhotoTag photoTag = new PhotoTag(photo, albumTag);
                    photoTagGenericRepo.save(photoTag);

                    // 캐시 정보가 있다면 수정하기
                    String key = descriptionRedisKeyPort.getKey(photoId);
                    Object isGetRedis = descriptionRedisGenericRepo.get(key);
                    if(isGetRedis != null) {
                        DescriptionResponseDto dto = objectMapper.convertValue(isGetRedis, DescriptionResponseDto.class);
                        dto.setPhotoTags(photoTagGetPort.photoTagGet(photoId));
                        descriptionRedisGenericRepo.set(key, dto);
                    }
                }
        );
    }
}
