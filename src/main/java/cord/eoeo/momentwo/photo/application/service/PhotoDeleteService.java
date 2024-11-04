package cord.eoeo.momentwo.photo.application.service;

import cord.eoeo.momentwo.config.s3.S3Manager;
import cord.eoeo.momentwo.image.application.port.out.ImageDeletePort;
import cord.eoeo.momentwo.photo.adapter.dto.PhotoDeleteRequestDto;
import cord.eoeo.momentwo.photo.advice.exception.NotDeleteImageException;
import cord.eoeo.momentwo.photo.application.port.in.PhotoDeleteUseCase;
import cord.eoeo.momentwo.photo.application.port.out.delete.PhotoDeleteAllSubAlbumIdAndPhotoIdPort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PhotoDeleteService implements PhotoDeleteUseCase {
    private final PhotoDeleteAllSubAlbumIdAndPhotoIdPort photoDeleteAllSubAlbumIdAndPhotoIdPort;
    private final ImageDeletePort imageDeletePort;
    private final S3Manager s3Manager;

    @Override
    @Transactional
    @CheckAlbumAccessRules
    public void photoDelete(PhotoDeleteRequestDto photoDeleteRequestDto) {
        if(photoDeleteRequestDto.getImagesId().isEmpty() || photoDeleteRequestDto.getImagesUrl().isEmpty()) {
            throw new NotDeleteImageException();
        }

        // 서버에서 삭제
        photoDeleteAllSubAlbumIdAndPhotoIdPort.deleteAllBySubAlbumIdAndPhotoId(
                photoDeleteRequestDto.getSubAlbumId(),
                photoDeleteRequestDto.getImagesId()
        );

        // 이미지 저장소 삭제
        photoDeleteRequestDto.getImagesUrl().forEach(image -> {
            imageDeletePort.imageDelete(s3Manager.getImagePath() + image).join();
        });
    }
}
