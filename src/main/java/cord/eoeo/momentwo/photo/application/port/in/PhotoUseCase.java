package cord.eoeo.momentwo.photo.application.port.in;

import cord.eoeo.momentwo.image.adapter.dto.ImageViewListResponseDto;
import cord.eoeo.momentwo.photo.adapter.dto.PhotoDeleteRequestDto;
import cord.eoeo.momentwo.photo.adapter.dto.PhotoUploadRequestDto;
import cord.eoeo.momentwo.photo.adapter.dto.PhotoViewRequestDto;
import org.springframework.data.domain.Pageable;

public interface PhotoUseCase {
    void photoUpload(PhotoUploadRequestDto photoUploadRequestDto);
    void photoDelete(PhotoDeleteRequestDto photoDeleteRequestDto);
    ImageViewListResponseDto photoView(long albumId, long subAlbumId, int size, long cursor);
}
