package cord.eoeo.momentwo.subAlbum.adapter.in;

import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumEditTitleRequestDto;
import cord.eoeo.momentwo.subAlbum.application.port.in.EditSubAlbumUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class EditSubAlbumTitleController {
    private final EditSubAlbumUseCase editSubAlbumUseCase;

    // 서브앨범 수정
    @PutMapping("/albums/sub/title")
    @ResponseStatus(HttpStatus.OK)
    public void editSubAlbumTitle(@RequestBody @Valid SubAlbumEditTitleRequestDto subAlbumEditTitleRequestDto) {
        editSubAlbumUseCase.editSubAlbumTitle(subAlbumEditTitleRequestDto);
    }
}
