package cord.eoeo.momentwo.subAlbum.adapter.in;

import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumCreateRequestDto;
import cord.eoeo.momentwo.subAlbum.application.port.in.CreateSubAlbumUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CreateSubAlbumController {
    private final CreateSubAlbumUseCase createSubAlbumUseCase;

    // 서브앨범 생성
    @PostMapping("/albums/sub")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSubAlbum(@RequestBody @Valid SubAlbumCreateRequestDto subAlbumCreateRequestDto) {
        createSubAlbumUseCase.createSubAlbum(subAlbumCreateRequestDto);
    }
}
