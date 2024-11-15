package cord.eoeo.momentwo.album.adapter.in.profile;

import cord.eoeo.momentwo.album.adapter.dto.AlbumProfileRequestDto;
import cord.eoeo.momentwo.album.application.port.in.profile.ProfileDeleteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AlbumProfileDeleteController {
    private final ProfileDeleteUseCase profileDeleteUseCase;

    // 프로필 삭제
    @DeleteMapping("/albums/profile/image")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void profileDelete(@ModelAttribute @Valid AlbumProfileRequestDto albumProfileRequestDto) {
        profileDeleteUseCase.profileDelete(albumProfileRequestDto);
    }
}
