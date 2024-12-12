package cord.eoeo.momentwo.album.adapter.in;

import cord.eoeo.momentwo.album.application.port.in.DeleteAlbumUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DeleteAlbumController {
    private final DeleteAlbumUseCase deleteAlbumUseCase;

    // 앨범 삭제
    @Validated
    @DeleteMapping("/albums/{albumId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbums(@PathVariable Long albumId) {
        deleteAlbumUseCase.deleteAlbums(albumId);
    }
}
