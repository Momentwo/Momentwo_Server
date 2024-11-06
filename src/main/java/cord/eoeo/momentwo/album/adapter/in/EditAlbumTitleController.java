package cord.eoeo.momentwo.album.adapter.in;

import cord.eoeo.momentwo.album.adapter.dto.AlbumTitleEditRequestDto;
import cord.eoeo.momentwo.album.application.port.in.EditAlbumTitleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class EditAlbumTitleController {
    private final EditAlbumTitleUseCase editAlbumTitleUseCase;

    // 앨범 수정
    @PutMapping("/albums/edit")
    @ResponseStatus(HttpStatus.OK)
    public void editAlbumsTitle(@RequestBody @Valid AlbumTitleEditRequestDto albumTitleEditRequestDto) {
        editAlbumTitleUseCase.editAlbumsTitle(albumTitleEditRequestDto);
    }
}
