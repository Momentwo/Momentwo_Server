package cord.eoeo.momentwo.album.application.port.in.profile;

public interface AlbumSubTitleDeleteUseCase {
    /**
     * 앨범 서브 타이틀 삭제
     * @param albumId
     * albumId : 앨범 아이디
     */
    void albumSubTitleDelete(Long albumId);
}
