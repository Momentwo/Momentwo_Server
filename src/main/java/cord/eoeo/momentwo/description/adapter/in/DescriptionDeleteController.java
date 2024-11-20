package cord.eoeo.momentwo.description.adapter.in;

import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionRequestDto;
import cord.eoeo.momentwo.description.application.port.in.DescriptionDeleteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class DescriptionDeleteController {
    private final DescriptionDeleteUseCase descriptionDeleteUseCase;

    @DeleteMapping("/descriptions/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDescription(@RequestBody @Valid DescriptionRequestDto descriptionRequestDto) {
        descriptionDeleteUseCase.deleteDescription(descriptionRequestDto);
    }
}
