package cord.eoeo.momentwo.image.application.port.in;

import cord.eoeo.momentwo.image.adapter.dto.PresignedRequestDto;
import cord.eoeo.momentwo.image.adapter.dto.PresignedResponseDto;
import cord.eoeo.momentwo.image.adapter.dto.UserPresignedRequestDto;

public interface ImageUseCase {
    PresignedResponseDto photoPresignedUrl(PresignedRequestDto presignedRequestDto);
    PresignedResponseDto albumProfilePresignedUrl(PresignedRequestDto presignedRequestDto);
    PresignedResponseDto userProfilePresignedUrl(UserPresignedRequestDto userPresignedRequestDto);
}
