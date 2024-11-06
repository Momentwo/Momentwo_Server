package cord.eoeo.momentwo.subAlbum.adapter.in;

import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumListResponseDto;
import cord.eoeo.momentwo.subAlbum.application.port.in.GetSubAlbumUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GetSubAlbumController {
    private final GetSubAlbumUseCase getSubAlbumUseCase;

    // 서브앨범 조회
    @GetMapping("/album/sub/{albumId}")
    @ResponseStatus(HttpStatus.OK)
    public SubAlbumListResponseDto getSubAlbums(@PathVariable long albumId) {
        return getSubAlbumUseCase.getSubAlbums(albumId);
    }
}