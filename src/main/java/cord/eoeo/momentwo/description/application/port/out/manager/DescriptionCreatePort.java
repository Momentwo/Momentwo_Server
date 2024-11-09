package cord.eoeo.momentwo.description.application.port.out.manager;

import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionCreateRequestDto;

public interface DescriptionCreatePort {
    void createDescription(DescriptionCreateRequestDto descriptionCreateRequestDto);
}
