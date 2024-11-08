package cord.eoeo.momentwo.like.application.service;

import cord.eoeo.momentwo.like.adapter.dto.in.PhotoLikesRequestDto;
import cord.eoeo.momentwo.like.application.port.in.UnDoLikesUseCase;
import cord.eoeo.momentwo.like.application.port.out.manager.UnDoLikesPort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UnDoLikesService implements UnDoLikesUseCase {
    private final UnDoLikesPort unDoLikesPort;
    @Override
    @CheckAlbumAccessRules
    @Transactional
    public void undoLikes(PhotoLikesRequestDto photoLikesRequestDto) {
        unDoLikesPort.undoLikes(photoLikesRequestDto.getPhotoId());
    }
}
