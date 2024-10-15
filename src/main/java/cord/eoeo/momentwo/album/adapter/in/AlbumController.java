package cord.eoeo.momentwo.album.adapter.in;

import cord.eoeo.momentwo.album.adapter.dto.*;
import cord.eoeo.momentwo.album.application.port.in.AlbumUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/albums")
public class AlbumController {
    private final AlbumUseCase albumUseCase;

    // 앨범 생성
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public void createAlbums(@RequestBody @Valid AlbumCreateRequestDto albumCreateRequestDto) {
        albumUseCase.createAlbums(albumCreateRequestDto);
    }

    // 앨범 수정
    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public void editAlbumsTitle(@RequestBody @Valid AlbumTitleEditRequestDto albumTitleEditRequestDto) {
        albumUseCase.editAlbumsTitle(albumTitleEditRequestDto);
    }

    // 앨범 삭제
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAlbums(@ModelAttribute @Valid AlbumDeleteRequestDto albumDeleteRequestDto) {
        albumUseCase.deleteAlbums(albumDeleteRequestDto);
    }

    // 앨범 전체 목록 조회
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public AlbumInfoListResponseDto getAlbums() {
        return albumUseCase.getAlbums();
    }

    // 앨범 개인 권한 요청
    @GetMapping("/rules/{albumId}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumRulesResponseDto getAlbumsRules(@PathVariable long albumId) {
        return albumUseCase.getAlbumsRules(albumId);
    }
}
