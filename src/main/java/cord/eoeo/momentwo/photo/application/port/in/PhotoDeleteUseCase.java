package cord.eoeo.momentwo.photo.application.port.in;

import java.util.List;

public interface PhotoDeleteUseCase {
    /**
     * 사진 삭제
     * @param albumId
     * @param subAlbumId
     * @param imagesId
     * albumId : 앨범 아이디
     * subAlbumId : 서브 앨범 아이디
     * (리스트)imagesId : 이미지 아이디
     */
    void photoDelete(Long albumId, Long subAlbumId, List<Long> imagesId);
}
