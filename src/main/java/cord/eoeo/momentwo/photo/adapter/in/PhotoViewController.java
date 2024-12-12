package cord.eoeo.momentwo.photo.adapter.in;

import cord.eoeo.momentwo.image.adapter.dto.ImageViewListResponseDto;
import cord.eoeo.momentwo.photo.application.port.in.PhotoViewUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PhotoViewController {
    private final PhotoViewUseCase photoViewUseCase;

    // 조회 (이미지 보기)
    @GetMapping("/albums/{albumId}/sub/{subAlbumId}/photos")
    @ResponseStatus(HttpStatus.OK)
    public ImageViewListResponseDto photoView(
            @PathVariable long albumId,
            @PathVariable long subAlbumId,
            @RequestParam int size,
            @RequestParam(required = false, defaultValue = "0") long cursor
    ) {
        return photoViewUseCase.photoView(albumId, subAlbumId, size, cursor);
    }
}
