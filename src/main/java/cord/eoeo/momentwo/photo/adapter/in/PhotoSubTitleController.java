package cord.eoeo.momentwo.photo.adapter.in;

import cord.eoeo.momentwo.photo.adapter.dto.sub.PhotoSubTitleGetRequestDto;
import cord.eoeo.momentwo.photo.adapter.dto.sub.PhotoSubTitleListResponseDto;
import cord.eoeo.momentwo.photo.adapter.dto.sub.SubTitleCreateRequestDto;
import cord.eoeo.momentwo.photo.application.port.in.PhotoSubTitleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/photos/sub")
public class PhotoSubTitleController {
    private final PhotoSubTitleUseCase photoSubTitleUseCase;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public void createPhotoSubTitle(@RequestBody @Valid SubTitleCreateRequestDto subTitleCreateRequestDto) {
        photoSubTitleUseCase.createPhotoSubTitle(subTitleCreateRequestDto);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<PhotoSubTitleListResponseDto> getPhotoSubTitle(
            @RequestBody @Valid PhotoSubTitleGetRequestDto photoSubTitleGetRequestDto) {
        return photoSubTitleUseCase.getPhotoSubTitle(photoSubTitleGetRequestDto);
    }
}
