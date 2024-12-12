package cord.eoeo.momentwo.photo.application.service;

import cord.eoeo.momentwo.photo.advice.exception.NotDeleteImageException;
import cord.eoeo.momentwo.photo.application.port.in.PhotoDeleteUseCase;
import cord.eoeo.momentwo.photo.application.port.out.PhotoS3ListDeletePort;
import cord.eoeo.momentwo.photo.application.port.out.delete.PhotoDeleteAllSubAlbumIdAndPhotoIdPort;
import cord.eoeo.momentwo.photo.application.port.out.find.PhotoFindAllImageRootByIdPort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoDeleteService implements PhotoDeleteUseCase {
    private final PhotoDeleteAllSubAlbumIdAndPhotoIdPort photoDeleteAllSubAlbumIdAndPhotoIdPort;
    private final PhotoFindAllImageRootByIdPort photoFindAllImageRootByIdPort;
    private final PhotoS3ListDeletePort photoS3ListDeletePort;

    @Override
    @Transactional
    @CheckAlbumAccessRules
    public void photoDelete(Long albumId, Long subAlbumId, List<Long> imagesId) {
        if(imagesId.isEmpty()) {
            throw new NotDeleteImageException();
        }

        // 이미지 저장소 삭제
        List<String> imagesKey = photoFindAllImageRootByIdPort.photoFindAllImageRootById(imagesId);
        photoS3ListDeletePort.photoS3ListDelete(imagesKey);

        // 서버에서 삭제
        photoDeleteAllSubAlbumIdAndPhotoIdPort.deleteAllBySubAlbumIdAndPhotoId(
            subAlbumId,
            imagesId
        );
    }
}
