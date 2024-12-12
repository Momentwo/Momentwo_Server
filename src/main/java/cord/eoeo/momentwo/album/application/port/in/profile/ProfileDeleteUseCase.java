package cord.eoeo.momentwo.album.application.port.in.profile;

public interface ProfileDeleteUseCase {
    /**
     * 앨범 프로필 삭제
     * @param albumId
     * albumId : 앨범 아이디
     */
    // 프로필 삭제
    void profileDelete(Long albumId);
}
