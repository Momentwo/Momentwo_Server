package cord.eoeo.momentwo.album.adapter.in;

import cord.eoeo.momentwo.album.adapter.dto.out.AlbumInfoListResponseDto;
import cord.eoeo.momentwo.album.application.port.in.GetAlbumUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetAlbumController {
    private final GetAlbumUseCase getAlbumUseCase;

    // 앨범 전체 목록 조회
    @GetMapping("/albums")
    @ResponseStatus(HttpStatus.OK)
    public AlbumInfoListResponseDto getAlbums() {
        return getAlbumUseCase.getAlbums();
    }
}
