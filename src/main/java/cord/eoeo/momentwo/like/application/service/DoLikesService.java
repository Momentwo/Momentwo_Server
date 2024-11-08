package cord.eoeo.momentwo.like.application.service;

import cord.eoeo.momentwo.like.adapter.dto.in.PhotoLikesRequestDto;
import cord.eoeo.momentwo.like.application.port.in.DoLikesUseCase;
import cord.eoeo.momentwo.like.application.port.out.manager.DoLikesPort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DoLikesService implements DoLikesUseCase {
    private final DoLikesPort doLikesPort;

    @Override
    @CheckAlbumAccessRules
    @Transactional
    public void doLikes(PhotoLikesRequestDto photoLikesRequestDto) {
        doLikesPort.doLikes(photoLikesRequestDto.getPhotoId());
    }
}
