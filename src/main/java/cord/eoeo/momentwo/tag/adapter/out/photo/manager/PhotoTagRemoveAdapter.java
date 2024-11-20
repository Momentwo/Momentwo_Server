package cord.eoeo.momentwo.tag.adapter.out.photo.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import cord.eoeo.momentwo.description.adapter.dto.out.DescriptionResponseDto;
import cord.eoeo.momentwo.description.application.port.out.DescriptionRedisGenericRepo;
import cord.eoeo.momentwo.description.application.port.out.DescriptionRedisKeyPort;
import cord.eoeo.momentwo.description.application.port.out.manager.DescriptionGetPhotoPort;
import cord.eoeo.momentwo.tag.application.port.out.photo.PhotoTagGenericRepo;
import cord.eoeo.momentwo.tag.application.port.out.photo.manager.PhotoTagGetPort;
import cord.eoeo.momentwo.tag.application.port.out.photo.manager.PhotoTagRemovePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhotoTagRemoveAdapter implements PhotoTagRemovePort {
    private final PhotoTagGenericRepo photoTagGenericRepo;
    private final DescriptionGetPhotoPort descriptionGetPhotoPort;
    private final DescriptionRedisGenericRepo descriptionRedisGenericRepo;
    private final DescriptionRedisKeyPort descriptionRedisKeyPort;
    private final ObjectMapper objectMapper;
    private final PhotoTagGetPort photoTagGetPort;

    @Override
    public void photoTagRemove(long photoId, long photoTagId) {
        // 작성자 확인
        descriptionGetPhotoPort.getPhoto(photoId);

        photoTagGenericRepo.deleteById(photoTagId);

        // 캐시 정보가 있다면 수정하기
        String key = descriptionRedisKeyPort.getKey(photoId);
        Object isGetRedis = descriptionRedisGenericRepo.get(key);
        if(isGetRedis != null) {
            DescriptionResponseDto dto = objectMapper.convertValue(isGetRedis, DescriptionResponseDto.class);
            dto.setPhotoTags(photoTagGetPort.photoTagGet(photoId));
            descriptionRedisGenericRepo.set(key, dto);
        }
    }
}
