package cord.eoeo.momentwo.photo.adapter.in;

import cord.eoeo.momentwo.image.adapter.dto.ImageViewListResponseDto;
import cord.eoeo.momentwo.photo.adapter.dto.PhotoDeleteRequestDto;
import cord.eoeo.momentwo.photo.adapter.dto.PhotoUploadRequestDto;
import cord.eoeo.momentwo.photo.application.port.in.PhotoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public void photoUpload(@RequestBody @Valid PhotoUploadRequestDto photoUploadRequestDto) {
        photoUseCase.photoUpload(photoUploadRequestDto);
    }

    // 삭제
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void photoDelete(@RequestBody @Valid PhotoDeleteRequestDto photoDeleteRequestDto) {
        photoUseCase.photoDelete(photoDeleteRequestDto);
    }

    // 조회 (이미지 보기)
    @GetMapping("/view/{albumId}/{subAlbumId}")
    @ResponseStatus(HttpStatus.OK)
    public ImageViewListResponseDto photoView(
            @PathVariable long albumId,
            @PathVariable long subAlbumId,
            @RequestParam int size,
            @RequestParam(required = false, defaultValue = "0") long cursor
    ) {
        return photoUseCase.photoView(albumId, subAlbumId, size, cursor);
    }
}
