package cord.eoeo.momentwo.description.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionEditRequestDto;
import cord.eoeo.momentwo.description.adapter.dto.out.DescriptionResponseDto;
import cord.eoeo.momentwo.description.application.port.in.DescriptionEditUseCase;
import cord.eoeo.momentwo.description.application.port.out.DescriptionRedisGenericRepo;
import cord.eoeo.momentwo.description.application.port.out.DescriptionRedisKeyPort;
import cord.eoeo.momentwo.description.application.port.out.manager.DescriptionEditPort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DescriptionEditService implements DescriptionEditUseCase {
    private final DescriptionEditPort descriptionEditPort;
    private final DescriptionRedisGenericRepo descriptionRedisGenericRepo;
    private final DescriptionRedisKeyPort descriptionRedisKeyPort;
    private final ObjectMapper objectMapper;

    @Override
    @CheckAlbumAccessRules
    @Transactional
    public void editDescription(DescriptionEditRequestDto descriptionEditRequestDto) {
        descriptionEditPort.editDescription(descriptionEditRequestDto);

        // 캐시 정보가 있다면 수정하기
        String key = descriptionRedisKeyPort.getKey(descriptionEditRequestDto.getPhotoId());
        Object isGetRedis = descriptionRedisGenericRepo.get(key);
        if(isGetRedis != null) {
            DescriptionResponseDto dto = objectMapper.convertValue(isGetRedis, DescriptionResponseDto.class);
            dto.setDescription(descriptionEditRequestDto.getEditDescription());
            descriptionRedisGenericRepo.set(key, dto);
        }
    }
}
