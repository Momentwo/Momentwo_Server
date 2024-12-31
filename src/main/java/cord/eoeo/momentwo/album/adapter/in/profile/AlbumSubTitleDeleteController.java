package cord.eoeo.momentwo.album.adapter.in.profile;

import cord.eoeo.momentwo.album.application.port.in.profile.AlbumSubTitleDeleteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AlbumSubTitleDeleteController {
    private final AlbumSubTitleDeleteUseCase albumSubTitleDeleteUseCase;

    // 서브 타이틀 삭제
    @DeleteMapping("/albums/{albumId}/subtitle")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void albumSubTitleDelete(@PathVariable Long albumId) {
        albumSubTitleDeleteUseCase.albumSubTitleDelete(albumId);
    }
}
