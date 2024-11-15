package cord.eoeo.momentwo.album.adapter.in;

import cord.eoeo.momentwo.album.adapter.dto.AlbumDeleteRequestDto;
import cord.eoeo.momentwo.album.application.port.in.DeleteAlbumUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class DeleteAlbumController {
    private final DeleteAlbumUseCase deleteAlbumUseCase;

    // 앨범 삭제
    @DeleteMapping("/albums/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbums(@ModelAttribute @Valid AlbumDeleteRequestDto albumDeleteRequestDto) {
        deleteAlbumUseCase.deleteAlbums(albumDeleteRequestDto);
    }
}
