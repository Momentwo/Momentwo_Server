package cord.eoeo.momentwo.album.adapter.in;

import cord.eoeo.momentwo.album.adapter.dto.*;
import cord.eoeo.momentwo.album.application.port.in.AlbumProfileUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/albums")
public class AlbumProfileController {
    private final AlbumProfileUseCase albumProfileUseCase;

    // 프로필 업로드 (변경)
    @PostMapping("/profile/image/upload")
    @ResponseStatus(HttpStatus.OK)
    public void profileUpload(@RequestBody @Valid AlbumProfileUploadRequestDto uploadRequestDto) {
        albumProfileUseCase.profileUpload(uploadRequestDto);
    }

    // 프로필 삭제
    @DeleteMapping("/profile/image")
    @ResponseStatus(HttpStatus.OK)
    public void profileDelete(@ModelAttribute @Valid AlbumProfileRequestDto albumProfileRequestDto) {
        albumProfileUseCase.profileDelete(albumProfileRequestDto);
    }

    // 서브 타이틀 수정
    @PutMapping("/subtitle")
    @ResponseStatus(HttpStatus.OK)
    public void albumSubTitleEdit(@RequestBody @Valid AlbumSubTitleEditRequestDto subTitleEditRequestDto) {
        albumProfileUseCase.albumSubTitleEdit(subTitleEditRequestDto);
    }

    // 서브 타이틀 삭제
    @DeleteMapping("/subtitle")
    @ResponseStatus(HttpStatus.OK)
    public void albumSubTitleDelete(@ModelAttribute @Valid AlbumProfileRequestDto albumProfileRequestDto) {
        albumProfileUseCase.albumSubTitleDelete(albumProfileRequestDto);
    }
}
