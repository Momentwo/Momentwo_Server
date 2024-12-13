package cord.eoeo.momentwo.tag.adapter.in.album;

import cord.eoeo.momentwo.tag.adapter.dto.out.album.AlbumTagListResponseDto;
import cord.eoeo.momentwo.tag.application.port.in.album.AlbumTagGetUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AlbumTagGetController {
    private final AlbumTagGetUseCase albumTagGetUseCase;

    @GetMapping("/albums/tags/{albumId}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumTagListResponseDto albumTagGet(@PathVariable long albumId) {
        return albumTagGetUseCase.albumTagGet(albumId);
    }
}
