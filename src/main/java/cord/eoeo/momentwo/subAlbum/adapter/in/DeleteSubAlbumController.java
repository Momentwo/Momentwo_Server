package cord.eoeo.momentwo.subAlbum.adapter.in;

import cord.eoeo.momentwo.subAlbum.application.port.in.DeleteSubAlbumUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DeleteSubAlbumController {
    private final DeleteSubAlbumUseCase deleteSubAlbumUseCase;

    // 서브앨범 삭제
    @DeleteMapping("/albums/{albumId}/sub/{subAlbumIds}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubAlbums(@PathVariable Long albumId, @PathVariable List<Long> subAlbumIds) {
        deleteSubAlbumUseCase.deleteSubAlbums(albumId, subAlbumIds);
    }
}
