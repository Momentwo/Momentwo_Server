package cord.eoeo.momentwo.photo.application.port.in;

import cord.eoeo.momentwo.photo.adapter.dto.PhotoDeleteRequestDto;
import cord.eoeo.momentwo.photo.adapter.dto.PhotoUploadRequestDto;
import cord.eoeo.momentwo.photo.adapter.dto.PhotoViewListResponseDto;

public interface PhotoUseCase {
    void photoUpload(PhotoUploadRequestDto photoUploadRequestDto);
    void photoDelete(PhotoDeleteRequestDto photoDeleteRequestDto);
    PhotoViewListResponseDto photoView(long albumId);
}
