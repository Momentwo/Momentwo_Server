package cord.eoeo.momentwo.subAlbum.adapter.in;

import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumCreateRequestDto;
import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumDeleteRequestDto;
import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumEditTitleRequestDto;
import cord.eoeo.momentwo.subAlbum.adapter.dto.SubAlbumListResponseDto;
import cord.eoeo.momentwo.subAlbum.application.port.in.SubAlbumUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/album/sub")
public class SubAlbumController {
    private final SubAlbumUseCase subAlbumUseCase;

    // 서브앨범 생성
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public void createSubAlbum(@RequestBody @Valid SubAlbumCreateRequestDto subAlbumCreateRequestDto) {
        subAlbumUseCase.createSubAlbum(subAlbumCreateRequestDto);
    }

    // 서브앨범 조회
    @GetMapping("/{albumId}")
    @ResponseStatus(HttpStatus.OK)
    public SubAlbumListResponseDto getSubAlbums(@PathVariable long albumId) {
        return subAlbumUseCase.getSubAlbums(albumId);
    }

    // 서브앨범 수정
    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public void editSubAlbumTitle(@RequestBody @Valid SubAlbumEditTitleRequestDto subAlbumEditTitleRequestDto) {
        subAlbumUseCase.editSubAlbumTitle(subAlbumEditTitleRequestDto);
    }

    // 서브앨범 삭제
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSubAlbums(@RequestBody @Valid SubAlbumDeleteRequestDto subAlbumDeleteRequestDto) {
        subAlbumUseCase.deleteSubAlbums(subAlbumDeleteRequestDto);
    }
}
