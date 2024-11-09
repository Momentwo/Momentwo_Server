package cord.eoeo.momentwo.description.application.service;

import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionEditRequestDto;
import cord.eoeo.momentwo.description.application.port.in.DescriptionEditUseCase;
import cord.eoeo.momentwo.description.application.port.out.manager.DescriptionEditPort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DescriptionEditService implements DescriptionEditUseCase {
    private final DescriptionEditPort descriptionEditPort;

    @Override
    @CheckAlbumAccessRules
    @Transactional
    public void editDescription(DescriptionEditRequestDto descriptionEditRequestDto) {
        descriptionEditPort.editDescription(descriptionEditRequestDto);
    }
}
