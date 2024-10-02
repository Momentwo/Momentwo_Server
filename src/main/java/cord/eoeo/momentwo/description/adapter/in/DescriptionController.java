package cord.eoeo.momentwo.description.adapter.in;

import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionCreateRequestDto;
import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionRequestDto;
import cord.eoeo.momentwo.description.adapter.dto.in.DescriptionEditRequestDto;
import cord.eoeo.momentwo.description.adapter.dto.out.DescriptionResponseDto;
import cord.eoeo.momentwo.description.application.port.in.DescriptionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/descriptions")
public class DescriptionController {
    private final DescriptionUseCase descriptionUseCase;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public void createDescription(@RequestBody @Valid DescriptionCreateRequestDto descriptionCreateRequestDto) {
        descriptionUseCase.createDescription(descriptionCreateRequestDto);
    }

    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public void editDescription(@RequestBody @Valid DescriptionEditRequestDto descriptionEditRequestDto) {
        descriptionUseCase.editDescription(descriptionEditRequestDto);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDescription(@RequestBody @Valid DescriptionRequestDto descriptionRequestDto) {
        descriptionUseCase.deleteDescription(descriptionRequestDto);
    }

    @GetMapping("/{albumId}/{photoId}")
    @ResponseStatus(HttpStatus.OK)
    public DescriptionResponseDto getDescription(
            @PathVariable long albumId,
            @PathVariable long photoId
    ) {
        return descriptionUseCase.getDescription(albumId, photoId);
    }
}
