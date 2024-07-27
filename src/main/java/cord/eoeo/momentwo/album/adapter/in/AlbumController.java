package cord.eoeo.momentwo.album.adapter.in;

import cord.eoeo.momentwo.album.adapter.dto.AlbumCreateRequestDto;
import cord.eoeo.momentwo.album.adapter.dto.AlbumTitleEditRequestDto;
import cord.eoeo.momentwo.album.application.port.in.AlbumUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumUseCase albumUseCase;

    // 앨범 생성
    @PostMapping("/albums/create")
    @ResponseStatus(HttpStatus.OK)
    public void createAlbums(@RequestBody @Valid AlbumCreateRequestDto albumCreateRequestDto) {
        albumUseCase.createAlbums(albumCreateRequestDto);
    }

    // 앨범 수정
    @PutMapping("/albums/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editAlbumsTitle(@PathVariable long id,
                                @ModelAttribute @Valid AlbumTitleEditRequestDto albumTitleEditRequestDto) {
        albumUseCase.editAlbumsTitle(id, albumTitleEditRequestDto);
    }

    // 앨범 삭제
    @DeleteMapping("/albums/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAlbums(@PathVariable long id) {
        albumUseCase.deleteAlbums(id);
    }
}
