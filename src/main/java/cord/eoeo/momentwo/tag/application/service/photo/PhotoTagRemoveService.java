package cord.eoeo.momentwo.tag.application.service.photo;

import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.tag.adapter.dto.in.photo.PhotoTagRemoveRequestDto;
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
    public void photoTagRemove(PhotoTagRemoveRequestDto photoTagRemoveRequestDto) {
        photoTagRemovePort.photoTagRemove(
                photoTagRemoveRequestDto.getPhotoId(),
                photoTagRemoveRequestDto.getPhotoTagId()
        );
    }
}
