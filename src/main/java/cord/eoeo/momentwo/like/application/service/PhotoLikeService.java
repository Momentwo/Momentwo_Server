package cord.eoeo.momentwo.like.application.service;

import cord.eoeo.momentwo.like.adapter.dto.in.PhotoLikesRequestDto;
import cord.eoeo.momentwo.like.adapter.dto.out.PhotoLikesResponseDto;
import cord.eoeo.momentwo.like.application.port.in.PhotoLikeUseCase;
import cord.eoeo.momentwo.like.application.port.out.PhotoLikesManager;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhotoLikeService implements PhotoLikeUseCase {
    private final PhotoLikesManager photoLikesManager;

    @Override
    @CheckAlbumAccessRules
    public void doLikes(PhotoLikesRequestDto photoLikesRequestDto) {
        photoLikesManager.doLikes(photoLikesRequestDto.getPhotoId());
    }

    @Override
    @CheckAlbumAccessRules
    public void undoLikes(PhotoLikesRequestDto photoLikesRequestDto) {
        photoLikesManager.undoLikes(photoLikesRequestDto.getPhotoId());
    }

    @Override
    @CheckAlbumAccessRules
    public PhotoLikesResponseDto getLikes(PhotoLikesRequestDto photoLikesRequestDto) {
        return new PhotoLikesResponseDto()
                .toDo(photoLikesManager.getLikes(photoLikesRequestDto.getPhotoId()));
    }
}
