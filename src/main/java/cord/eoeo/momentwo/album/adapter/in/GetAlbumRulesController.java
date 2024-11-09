package cord.eoeo.momentwo.album.adapter.in;

import cord.eoeo.momentwo.album.adapter.dto.AlbumRulesResponseDto;
import cord.eoeo.momentwo.album.application.port.in.GetAlbumRulesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetAlbumRulesController {
    private final GetAlbumRulesUseCase getAlbumRulesUseCase;

    // 앨범 개인 권한 요청
    @GetMapping("/albums/rules/{albumId}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumRulesResponseDto getAlbumsRules(@PathVariable long albumId) {
        return getAlbumRulesUseCase.getAlbumsRules(albumId);
    }
}
