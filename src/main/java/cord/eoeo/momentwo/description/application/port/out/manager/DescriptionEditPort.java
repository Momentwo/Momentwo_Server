package cord.eoeo.momentwo.description.application.port.out.manager;

import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionEditRequestDto;

public interface DescriptionEditPort {
    void editDescription(DescriptionEditRequestDto descriptionEditRequestDto);
}
