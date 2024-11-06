package cord.eoeo.momentwo.member.application.port.out.jpa;

import cord.eoeo.momentwo.member.application.port.out.AlbumMemberGenericJpaRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AlbumMemberDeleteJpaRepo extends AlbumMemberGenericJpaRepo {
    // 앨범에 속한 멤버 삭제
    @Modifying
    @Query("DELETE FROM Member m WHERE m.album.id = :albumId AND m.user.id = :userId")
    int deleteByAlbumIdAndUserId(long albumId, long userId);
}
