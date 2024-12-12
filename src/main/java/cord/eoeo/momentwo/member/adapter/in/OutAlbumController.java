package cord.eoeo.momentwo.member.adapter.in;

import cord.eoeo.momentwo.member.application.port.in.OutAlbumUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OutAlbumController {
    private final OutAlbumUseCase outAlbumUseCase;

    // 멤버 나가기 (개인)
    @DeleteMapping("/albums/{albumId}/members/self")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void outAlbum(@PathVariable Long albumId) {
        outAlbumUseCase.outAlbum(albumId);
    }
}
