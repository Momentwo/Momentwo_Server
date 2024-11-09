package cord.eoeo.momentwo.description.application.port.out.manager;

import cord.eoeo.momentwo.description.adapter.dto.out.DescriptionResponseDto;

public interface DescriptionGetPort {
    DescriptionResponseDto getDescription(long photoId);
}
