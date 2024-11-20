package cord.eoeo.momentwo.tag.adapter.in.album;

import cord.eoeo.momentwo.tag.adapter.dto.in.album.AlbumTagCreateRequestDto;
import cord.eoeo.momentwo.tag.application.port.in.album.AlbumTagCreateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AlbumTagCreateController {
    private final AlbumTagCreateUseCase albumTagCreateUseCase;

    @PostMapping("/albums/tags")
    @ResponseStatus(HttpStatus.CREATED)
    public void albumTagCreate(@RequestBody @Valid AlbumTagCreateRequestDto albumTagCreateRequestDto) {
        albumTagCreateUseCase.albumTagCreate(albumTagCreateRequestDto);
    }
}
