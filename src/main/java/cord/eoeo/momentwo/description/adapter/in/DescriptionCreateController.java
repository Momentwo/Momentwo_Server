package cord.eoeo.momentwo.description.adapter.in;

import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionCreateRequestDto;
import cord.eoeo.momentwo.description.application.port.in.DescriptionCreateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class DescriptionCreateController {
    private final DescriptionCreateUseCase descriptionCreateUseCase;

    @PostMapping("/descriptions/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDescription(@RequestBody @Valid DescriptionCreateRequestDto descriptionCreateRequestDto) {
        descriptionCreateUseCase.createDescription(descriptionCreateRequestDto);
    }
}
