package cord.eoeo.momentwo.description.application.service;

import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionCreateRequestDto;
import cord.eoeo.momentwo.description.application.port.in.DescriptionCreateUseCase;
import cord.eoeo.momentwo.description.application.port.out.manager.DescriptionCreatePort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DescriptionCreateService implements DescriptionCreateUseCase {
    private final DescriptionCreatePort descriptionCreatePort;

    @Override
    @CheckAlbumAccessRules
    @Transactional
    public void createDescription(DescriptionCreateRequestDto descriptionCreateRequestDto) {
        descriptionCreatePort.createDescription(descriptionCreateRequestDto);
    }
}
