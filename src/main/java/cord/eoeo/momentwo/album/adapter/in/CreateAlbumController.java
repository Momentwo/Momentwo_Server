package cord.eoeo.momentwo.album.adapter.in;

import cord.eoeo.momentwo.album.adapter.dto.in.AlbumCreateRequestDto;
import cord.eoeo.momentwo.album.application.port.in.CreateAlbumUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CreateAlbumController {
    private final CreateAlbumUseCase createAlbumUseCase;

    // 앨범 생성
    @PostMapping("/albums")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAlbums(@RequestBody @Valid AlbumCreateRequestDto albumCreateRequestDto) {
        createAlbumUseCase.createAlbums(albumCreateRequestDto);
    }
}
