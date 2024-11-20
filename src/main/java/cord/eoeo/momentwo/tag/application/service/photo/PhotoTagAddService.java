package cord.eoeo.momentwo.tag.application.service.photo;

import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.tag.adapter.dto.in.photo.PhotoTagAddRequestDto;
import cord.eoeo.momentwo.tag.application.port.in.photo.PhotoTagAddUseCase;
import cord.eoeo.momentwo.tag.application.port.out.photo.manager.PhotoTagAddPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PhotoTagAddService implements PhotoTagAddUseCase {
    private final PhotoTagAddPort photoTagAddPort;

    @Override
    @Transactional
    @CheckAlbumAccessRules
    public void photoTagAdd(PhotoTagAddRequestDto photoTagAddRequestDto) {
        photoTagAddPort.PhotoTagAdd(
                photoTagAddRequestDto.getAlbumId(),
                photoTagAddRequestDto.getPhotoId(),
                photoTagAddRequestDto.getAlbumTagId()
        );
    }
}
