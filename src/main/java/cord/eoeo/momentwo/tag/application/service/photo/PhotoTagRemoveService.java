package cord.eoeo.momentwo.tag.application.service.photo;

import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.tag.application.port.in.photo.PhotoTagRemoveUseCase;
import cord.eoeo.momentwo.tag.application.port.out.photo.manager.PhotoTagRemovePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PhotoTagRemoveService implements PhotoTagRemoveUseCase {
    private final PhotoTagRemovePort photoTagRemovePort;

    @Override
    @Transactional
    @CheckAlbumAccessRules
    public void photoTagRemove(Long albumId, Long photoId, Long photoTagId) {
        photoTagRemovePort.photoTagRemove(
                photoId,
                photoTagId
        );
    }
}
