package cord.eoeo.momentwo.member.application.port.in;

public interface OutAlbumUseCase {
    /**
     * 앨범 멤버 나가기
     * @param albumId
     * albumId : 앨범 아이디
     */
    void outAlbum(Long albumId);
}
