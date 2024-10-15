package cord.eoeo.momentwo.image.application.port.in;

import cord.eoeo.momentwo.image.adapter.dto.PresignedRequestDto;
import cord.eoeo.momentwo.image.adapter.dto.PresignedResponseDto;

public interface ImageUseCase {
    PresignedResponseDto photoPresignedUrl(PresignedRequestDto presignedRequestDto);
    PresignedResponseDto albumProfilePresignedUrl(PresignedRequestDto presignedRequestDto);
}
