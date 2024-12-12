package cord.eoeo.momentwo.album.adapter.in.profile;

import cord.eoeo.momentwo.album.application.port.in.profile.ProfileDeleteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AlbumProfileDeleteController {
    private final ProfileDeleteUseCase profileDeleteUseCase;

    // 프로필 삭제
    @DeleteMapping("/albums/{albumId}/profile/image")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void profileDelete(@PathVariable Long albumId) {
        profileDeleteUseCase.profileDelete(albumId);
    }
}
