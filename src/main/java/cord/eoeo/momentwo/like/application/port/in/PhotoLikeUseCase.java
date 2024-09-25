package cord.eoeo.momentwo.like.application.port.in;

import cord.eoeo.momentwo.like.adapter.dto.in.PhotoLikesRequestDto;
import cord.eoeo.momentwo.like.adapter.dto.out.PhotoLikesResponseDto;

public interface PhotoLikeUseCase {
    void doLikes(PhotoLikesRequestDto photoLikesRequestDto);
    void undoLikes(PhotoLikesRequestDto photoLikesRequestDto);
    PhotoLikesResponseDto getLikes(PhotoLikesRequestDto photoLikesRequestDto);
}
