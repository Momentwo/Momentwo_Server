package cord.eoeo.momentwo.tag.adapter.in.album;

import cord.eoeo.momentwo.tag.adapter.dto.in.album.AlbumTagDeleteRequestDto;
import cord.eoeo.momentwo.tag.application.port.in.album.AlbumTagDeleteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AlbumTagDeleteController {
    private final AlbumTagDeleteUseCase albumTagDeleteUseCase;

    @DeleteMapping("/albums/tags")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void albumTagDelete(@RequestBody @Valid AlbumTagDeleteRequestDto albumTagDeleteRequestDto) {
        albumTagDeleteUseCase.albumTagDelete(albumTagDeleteRequestDto);
    }
}
