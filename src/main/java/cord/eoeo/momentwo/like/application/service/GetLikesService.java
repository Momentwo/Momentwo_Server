package cord.eoeo.momentwo.like.application.service;

import cord.eoeo.momentwo.like.adapter.dto.out.PhotoLikesResponseDto;
import cord.eoeo.momentwo.like.application.port.in.GetLikesUseCase;
import cord.eoeo.momentwo.like.application.port.out.manager.GetLikesPort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetLikesService implements GetLikesUseCase {
    private final GetLikesPort getLikesPort;

    @Override
    @CheckAlbumAccessRules
    @Transactional(readOnly = true)
    public PhotoLikesResponseDto getLikes(long albumId, long photoId) {
        return new PhotoLikesResponseDto()
                .toDo(getLikesPort.getLikes(photoId));
    }
}
