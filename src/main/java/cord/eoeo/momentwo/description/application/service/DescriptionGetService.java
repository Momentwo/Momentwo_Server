package cord.eoeo.momentwo.description.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import cord.eoeo.momentwo.description.adapter.dto.out.DescriptionResponseDto;
import cord.eoeo.momentwo.description.application.port.in.DescriptionGetUseCase;
import cord.eoeo.momentwo.description.application.port.out.DescriptionRedisGenericRepo;
import cord.eoeo.momentwo.description.application.port.out.DescriptionRedisKeyPort;
import cord.eoeo.momentwo.description.application.port.out.manager.DescriptionGetPort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DescriptionGetService implements DescriptionGetUseCase {
    private final DescriptionGetPort descriptionGetPort;
    private final DescriptionRedisGenericRepo descriptionRedisGenericRepo;
    private final DescriptionRedisKeyPort descriptionRedisKeyPort;
    private final ObjectMapper objectMapper;

    @Override
    @CheckAlbumAccessRules
    @Transactional(readOnly = true)
    public DescriptionResponseDto getDescription(long albumId, long photoId) {
        // 캐시 데이터가 있는 경우 리턴
        String key = descriptionRedisKeyPort.getKey(photoId);
        Object isGetRedis = descriptionRedisGenericRepo.get(key);

        if(isGetRedis != null) {
            return objectMapper.convertValue(isGetRedis, DescriptionResponseDto.class);
        }

        // 캐시에 데이터가 없는 경우 DB 조회
        DescriptionResponseDto description = descriptionGetPort.getDescription(photoId);

        // DB 조회 데이터 캐시에 저장
        descriptionRedisGenericRepo.set(key, description);
        descriptionRedisGenericRepo.expire(key, 10L);

        return description;
    }
}
