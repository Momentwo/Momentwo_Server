package cord.eoeo.momentwo.tag.adapter.in.album;

import cord.eoeo.momentwo.tag.application.port.in.album.AlbumTagDeleteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AlbumTagDeleteController {
    private final AlbumTagDeleteUseCase albumTagDeleteUseCase;

    @DeleteMapping("/albums/{albumId}/tags/{albumTagId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void albumTagDelete(@PathVariable Long albumId, @PathVariable Long albumTagId) {
        albumTagDeleteUseCase.albumTagDelete(albumId, albumTagId);
    }
}
