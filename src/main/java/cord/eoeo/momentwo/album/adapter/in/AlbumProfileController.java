package cord.eoeo.momentwo.album.adapter.in;

import cord.eoeo.momentwo.album.adapter.dto.*;
import cord.eoeo.momentwo.album.application.port.in.AlbumProfileUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AlbumProfileController {
    private final AlbumProfileUseCase albumProfileUseCase;

    // 프로필 업로드 (변경)
    @PutMapping("/albums/profile/image/upload")
    @ResponseStatus(HttpStatus.OK)
    public void profileUpload(@ModelAttribute @Valid AlbumProfileUploadRequestDto uploadRequestDto) {
        albumProfileUseCase.profileUpload(uploadRequestDto);
    }

    // 프로필 삭제
    @DeleteMapping("/albums/profile/image/{albumId}")
    @ResponseStatus(HttpStatus.OK)
    public void profileDelete(@PathVariable long albumId) {
        albumProfileUseCase.profileDelete(albumId);
    }

    // 서브 타이틀 수정
    @PutMapping("/albums/subtitle")
    @ResponseStatus(HttpStatus.OK)
    public void albumSubTitleEdit(@RequestBody @Valid AlbumSubTitleEditRequestDto subTitleEditRequestDto) {
        albumProfileUseCase.albumSubTitleEdit(subTitleEditRequestDto);
    }

    // 서브 타이틀 삭제
    @DeleteMapping("/albums/subtitle/{albumId}")
    @ResponseStatus(HttpStatus.OK)
    public void albumSubTitleDelete(@PathVariable long albumId) {
        albumProfileUseCase.albumSubTitleDelete(albumId);
    }
}
