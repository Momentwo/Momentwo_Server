package cord.eoeo.momentwo.subAlbum.application.port.in;

import java.util.List;

public interface DeleteSubAlbumUseCase {
    /**
     * 서브 앨범 삭제
     * @param albumId
     * @param subAlbumIds
     * albumId : 앨범 아이디
     * subAlbumIds : 서브 앨범의 아이디 정보
     */
    void deleteSubAlbums(Long albumId, List<Long> subAlbumIds);
}
