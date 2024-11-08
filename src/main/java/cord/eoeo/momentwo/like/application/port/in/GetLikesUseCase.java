package cord.eoeo.momentwo.like.application.port.in;

import cord.eoeo.momentwo.like.adapter.dto.out.PhotoLikesResponseDto;

public interface GetLikesUseCase {
    /**
     * 좋아요 갯수 조회
     * @param albumId : 앨범 아이디
     * @param photoId : 사진 아이디
     * @return : 카운트 갯수
     */
    PhotoLikesResponseDto getLikes(long albumId, long photoId);
}
