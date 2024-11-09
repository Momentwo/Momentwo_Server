package cord.eoeo.momentwo.description.adapter.in;

import cord.eoeo.momentwo.description.adapter.dto.out.DescriptionResponseDto;
import cord.eoeo.momentwo.description.application.port.in.DescriptionGetUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DescriptionGetController {
    private final DescriptionGetUseCase descriptionGetUseCase;

    @GetMapping("/descriptions/{albumId}/{photoId}")
    @ResponseStatus(HttpStatus.OK)
    public DescriptionResponseDto getDescription(
            @PathVariable long albumId,
            @PathVariable long photoId
    ) {
        return descriptionGetUseCase.getDescription(albumId, photoId);
    }
}
