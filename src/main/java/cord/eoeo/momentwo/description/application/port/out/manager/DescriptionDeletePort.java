package cord.eoeo.momentwo.description.application.port.out.manager;

import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionRequestDto;

public interface DescriptionDeletePort {
    void deleteDescription(DescriptionRequestDto descriptionRequestDto);
}
