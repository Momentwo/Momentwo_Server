package cord.eoeo.momentwo.description.application.service;

import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionCreateRequestDto;
import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionEditRequestDto;
import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionRequestDto;
import cord.eoeo.momentwo.description.adapter.dto.out.DescriptionResponseDto;
import cord.eoeo.momentwo.description.application.port.in.DescriptionUseCase;
import cord.eoeo.momentwo.description.application.port.out.DescriptionManager;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DescriptionService implements DescriptionUseCase {
    private final DescriptionManager descriptionManager;

    @Override
    @CheckAlbumAccessRules
    public void createDescription(DescriptionCreateRequestDto descriptionCreateRequestDto) {
        descriptionManager.createDescription(descriptionCreateRequestDto);
    }

    @Override
    @CheckAlbumAccessRules
    public void editDescription(DescriptionEditRequestDto descriptionEditRequestDto) {
        descriptionManager.editDescription(descriptionEditRequestDto);
    }

    @Override
    @CheckAlbumAccessRules
    public void deleteDescription(DescriptionRequestDto descriptionRequestDto) {
        descriptionManager.deleteDescription(descriptionRequestDto);
    }

    @Override
    @CheckAlbumAccessRules
    public DescriptionResponseDto getDescription(long albumId, long photoId) {
        return descriptionManager.getDescription(photoId);
    }
}
