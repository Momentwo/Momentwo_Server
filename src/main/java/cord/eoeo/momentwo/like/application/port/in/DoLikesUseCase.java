package cord.eoeo.momentwo.like.application.port.in;

import cord.eoeo.momentwo.like.adapter.dto.in.PhotoLikesRequestDto;

public interface DoLikesUseCase {
    /**
     * 좋아요 누르기
     * @param photoLikesRequestDto
     * albumId : 앨범 아이디
     * photoId : 사진 아이디
     */
    void doLikes(PhotoLikesRequestDto photoLikesRequestDto);
}
