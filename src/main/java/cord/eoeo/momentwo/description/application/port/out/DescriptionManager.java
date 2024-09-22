package cord.eoeo.momentwo.description.application.port.out;

import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionCreateRequestDto;
import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionEditRequestDto;
import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionRequestDto;
import cord.eoeo.momentwo.description.adapter.dto.out.DescriptionResponseDto;

public interface DescriptionManager {
    void createDescription(DescriptionCreateRequestDto descriptionCreateRequestDto);
    void editDescription(DescriptionEditRequestDto descriptionEditRequestDto);
    void deleteDescription(DescriptionRequestDto descriptionRequestDto);
    DescriptionResponseDto getDescription(DescriptionRequestDto descriptionRequestDto);
}
