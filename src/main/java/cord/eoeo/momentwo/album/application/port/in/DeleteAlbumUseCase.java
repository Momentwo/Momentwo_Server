package cord.eoeo.momentwo.album.application.port.in;

public interface DeleteAlbumUseCase {
    /**
     * 앨범 삭제
     * @param albumId
     * albumId : 앨범 아이디
     */
    void deleteAlbums(Long albumId);
}
