package cord.eoeo.momentwo.description.application.service;

import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionRequestDto;
import cord.eoeo.momentwo.description.application.port.in.DescriptionDeleteUseCase;
import cord.eoeo.momentwo.description.application.port.out.manager.DescriptionDeletePort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DescriptionDeleteService implements DescriptionDeleteUseCase {
    private final DescriptionDeletePort descriptionDeletePort;

    @Override
    @CheckAlbumAccessRules
    @Transactional
    public void deleteDescription(DescriptionRequestDto descriptionRequestDto) {
        descriptionDeletePort.deleteDescription(descriptionRequestDto);
    }
}
