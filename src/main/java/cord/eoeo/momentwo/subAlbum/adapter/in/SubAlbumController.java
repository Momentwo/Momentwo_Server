package cord.eoeo.momentwo.subAlbum.adapter.in;

import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumCreateRequestDto;
import cord.eoeo.momentwo.subAlbum.application.port.in.SubAlbumUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/album")
public class SubAlbumController {
    private final SubAlbumUseCase subAlbumUseCase;

    // 서브앨범 생성
    @PostMapping("/sub/create")
    @ResponseStatus(HttpStatus.OK)
    public void createSubAlbum(@RequestBody @Valid SubAlbumCreateRequestDto subAlbumCreateRequestDto) {
        subAlbumUseCase.createSubAlbum(subAlbumCreateRequestDto);
    }
}
