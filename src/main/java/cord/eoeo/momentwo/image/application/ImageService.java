package cord.eoeo.momentwo.image.application;

import cord.eoeo.momentwo.config.s3.S3Manager;
import cord.eoeo.momentwo.image.adapter.dto.PresignedRequestDto;
import cord.eoeo.momentwo.image.adapter.dto.PresignedResponseDto;
import cord.eoeo.momentwo.image.application.port.in.ImageUseCase;
import cord.eoeo.momentwo.image.application.port.out.ImageManager;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService implements ImageUseCase {
    private final ImageManager imageManager;
    private final S3Manager s3Manager;

    @Override
    @CheckAlbumAccessRules
    public PresignedResponseDto photoPresignedUrl(PresignedRequestDto presignedRequestDto) {
        return new PresignedResponseDto()
                .toDo(imageManager.getPresignedUrl(presignedRequestDto.getExtension(), s3Manager.getImagePath()));
    }

    @Override
    @CheckAlbumAccessRules
    public PresignedResponseDto albumProfilePresignedUrl(PresignedRequestDto presignedRequestDto) {
        return new PresignedResponseDto()
                .toDo(imageManager.getPresignedUrl(presignedRequestDto.getExtension(), s3Manager.getProfileAlbumPath()));
    }
}
