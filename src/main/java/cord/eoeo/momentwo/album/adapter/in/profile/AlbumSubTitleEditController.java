package cord.eoeo.momentwo.album.adapter.in.profile;

import cord.eoeo.momentwo.album.adapter.dto.in.AlbumSubTitleEditRequestDto;
import cord.eoeo.momentwo.album.application.port.in.profile.AlbumSubTitleEditUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AlbumSubTitleEditController {
    private final AlbumSubTitleEditUseCase albumSubTitleEditUseCase;

    // 서브 타이틀 수정
    @PutMapping("/albums/subtitle")
    @ResponseStatus(HttpStatus.OK)
    public void albumSubTitleEdit(@RequestBody @Valid AlbumSubTitleEditRequestDto subTitleEditRequestDto) {
        albumSubTitleEditUseCase.albumSubTitleEdit(subTitleEditRequestDto);
    }
}
