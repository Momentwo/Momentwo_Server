package cord.eoeo.momentwo.photo.application.port.in;

import cord.eoeo.momentwo.image.adapter.dto.ImageViewListResponseDto;
import cord.eoeo.momentwo.photo.adapter.dto.PhotoDeleteRequestDto;
import cord.eoeo.momentwo.photo.adapter.dto.PhotoUploadRequestDto;
import cord.eoeo.momentwo.photo.adapter.dto.PhotoViewRequestDto;

public interface PhotoUseCase {
    void photoUpload(PhotoUploadRequestDto photoUploadRequestDto);
    void photoDelete(PhotoDeleteRequestDto photoDeleteRequestDto);
    ImageViewListResponseDto photoView(PhotoViewRequestDto photoViewRequestDto);
}
