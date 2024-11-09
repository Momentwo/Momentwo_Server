package cord.eoeo.momentwo.photo.application.port.in;

import cord.eoeo.momentwo.image.adapter.dto.ImageViewListResponseDto;

public interface PhotoViewUseCase {
    /**
     * 사진 조회
     * @param albumId : 앨범 아이디
     * @param subAlbumId : 서브 앨범 아이디
     * @param size : 크기
     * @param cursor : 커서
     * @return : 이미지 URL
     */
    ImageViewListResponseDto photoView(long albumId, long subAlbumId, int size, long cursor);
}
