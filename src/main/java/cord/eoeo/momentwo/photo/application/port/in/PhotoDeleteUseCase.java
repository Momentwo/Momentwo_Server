package cord.eoeo.momentwo.photo.application.port.in;

import cord.eoeo.momentwo.photo.adapter.dto.PhotoDeleteRequestDto;

public interface PhotoDeleteUseCase {
    /**
     * 사진 삭제
     * @param photoDeleteRequestDto
     * albumId : 앨범 아이디
     * subAlbumId : 서브 앨범 아이디
     * (리스트)imagesId : 이미지 아이디
     * (리스트)imagesUrl : 이미지 url
     */
    void photoDelete(PhotoDeleteRequestDto photoDeleteRequestDto);
}
