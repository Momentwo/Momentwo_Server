package cord.eoeo.momentwo.photo.adapter.in;

import cord.eoeo.momentwo.photo.adapter.dto.PhotoMoveRequestDto;
import cord.eoeo.momentwo.photo.application.port.in.PhotoMoveUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PhotoMoveController {
    private final PhotoMoveUseCase photoMoveUseCase;

    @PutMapping("/albums/sub/photos/move")
    @ResponseStatus(HttpStatus.OK)
    public void photoMove(@RequestBody @Valid PhotoMoveRequestDto photoMoveRequestDto) {
        photoMoveUseCase.photoMove(photoMoveRequestDto);
    }
}
