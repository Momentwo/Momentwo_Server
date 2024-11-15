package cord.eoeo.momentwo.subAlbum.adapter.in;

import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumDeleteRequestDto;
import cord.eoeo.momentwo.subAlbum.application.port.in.DeleteSubAlbumUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class DeleteSubAlbumController {
    private final DeleteSubAlbumUseCase deleteSubAlbumUseCase;

    // 서브앨범 삭제
    @DeleteMapping("/album/sub/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubAlbums(@RequestBody @Valid SubAlbumDeleteRequestDto subAlbumDeleteRequestDto) {
        deleteSubAlbumUseCase.deleteSubAlbums(subAlbumDeleteRequestDto);
    }
}
