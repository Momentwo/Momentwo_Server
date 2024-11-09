package cord.eoeo.momentwo.photo.application.port.in;

import cord.eoeo.momentwo.photo.adapter.dto.PhotoMoveRequestDto;

public interface PhotoMoveUseCase {
    /**
     * 사진 이동
     * @param photoMoveRequestDto
     * albumId : 앨범 아이디
     * subAlbumId : 서브 앨범 아이디
     * photoId : 사진 아이디
     */
    void photoMove(PhotoMoveRequestDto photoMoveRequestDto);
}
