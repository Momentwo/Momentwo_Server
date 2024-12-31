package cord.eoeo.momentwo.description.adapter.in;

import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionEditRequestDto;
import cord.eoeo.momentwo.description.application.port.in.DescriptionEditUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class DescriptionEditController {
    private final DescriptionEditUseCase descriptionEditUseCase;

    @PutMapping("/albums/sub/photos/descriptions")
    @ResponseStatus(HttpStatus.OK)
    public void editDescription(@RequestBody @Valid DescriptionEditRequestDto descriptionEditRequestDto) {
        descriptionEditUseCase.editDescription(descriptionEditRequestDto);
    }
}
