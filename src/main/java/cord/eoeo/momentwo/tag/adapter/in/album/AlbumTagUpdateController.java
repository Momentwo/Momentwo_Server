package cord.eoeo.momentwo.tag.adapter.in.album;

import cord.eoeo.momentwo.tag.adapter.dto.in.album.AlbumTagUpdateRequestDto;
import cord.eoeo.momentwo.tag.application.port.in.album.AlbumTagUpdateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AlbumTagUpdateController {
    private final AlbumTagUpdateUseCase albumTagUpdateUseCase;

    @PutMapping("/albums/tags")
    @ResponseStatus(HttpStatus.OK)
    public void albumTagUpdate(@RequestBody @Valid AlbumTagUpdateRequestDto albumTagUpdateRequestDto) {
        albumTagUpdateUseCase.albumTagUpdate(albumTagUpdateRequestDto);
    }
}
