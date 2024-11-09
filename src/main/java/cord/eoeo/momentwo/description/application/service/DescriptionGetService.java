package cord.eoeo.momentwo.description.application.service;

import cord.eoeo.momentwo.description.adapter.dto.out.DescriptionResponseDto;
import cord.eoeo.momentwo.description.application.port.in.DescriptionGetUseCase;
import cord.eoeo.momentwo.description.application.port.out.manager.DescriptionGetPort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DescriptionGetService implements DescriptionGetUseCase {
    private final DescriptionGetPort descriptionGetPort;

    @Override
    @CheckAlbumAccessRules
    @Transactional(readOnly = true)
    public DescriptionResponseDto getDescription(long albumId, long photoId) {
        return descriptionGetPort.getDescription(photoId);
    }
}
