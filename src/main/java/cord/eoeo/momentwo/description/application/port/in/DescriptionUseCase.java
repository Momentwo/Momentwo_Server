package cord.eoeo.momentwo.description.application.port.in;

import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionCreateRequestDto;
import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionEditRequestDto;
import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionRequestDto;
import cord.eoeo.momentwo.description.adapter.dto.out.DescriptionResponseDto;

public interface DescriptionUseCase {
    void createDescription(DescriptionCreateRequestDto descriptionCreateRequestDto);
    void editDescription(DescriptionEditRequestDto descriptionEditRequestDto);
    void deleteDescription(DescriptionRequestDto descriptionRequestDto);
    DescriptionResponseDto getDescription(DescriptionRequestDto descriptionRequestDto);
}
