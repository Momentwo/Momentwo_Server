package cord.eoeo.momentwo.photo.adapter.in;

import cord.eoeo.momentwo.photo.adapter.dto.PhotoDeleteRequestDto;
import cord.eoeo.momentwo.photo.adapter.dto.PhotoUploadRequestDto;
import cord.eoeo.momentwo.photo.adapter.dto.PhotoViewListResponseDto;
import cord.eoeo.momentwo.photo.application.port.in.PhotoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/photos")
public class PhotoController {
    private final PhotoUseCase photoUseCase;

    // 업로드
    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    public void photoUpload(@ModelAttribute @Valid PhotoUploadRequestDto photoUploadRequestDto) {
        photoUseCase.photoUpload(photoUploadRequestDto);
    }

    // 삭제
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void photoDelete(@RequestBody @Valid PhotoDeleteRequestDto photoDeleteRequestDto) {
        photoUseCase.photoDelete(photoDeleteRequestDto);
    }

    // 조회 (이미지 보기)
    @GetMapping("/{albumId}/view")
    @ResponseStatus(HttpStatus.OK)
    public PhotoViewListResponseDto photoView(@PathVariable long albumId) {
        return photoUseCase.photoView(albumId);
    }
}
