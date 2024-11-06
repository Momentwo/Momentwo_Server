package cord.eoeo.momentwo.member.application.port.out.jpa;

import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.member.application.port.out.AlbumMemberGenericJpaRepo;
import cord.eoeo.momentwo.member.domain.Member;
import cord.eoeo.momentwo.user.domain.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlbumMemberFindJpaRepo extends AlbumMemberGenericJpaRepo {
    // 앨범에 해당하는 모든 멤버 찾기
    @Query("SELECT m FROM Member m JOIN m.user u JOIN m.album a WHERE a.id = :albumId")
    List<Member> findMemberByAlbum(long albumId);

    // 앨범에 속한 유저의 권한 찾기
    @Query("SELECT m FROM Member m JOIN m.user u JOIN m.album a WHERE a.id = :albumId and u.id = :userId")
    Optional<Member> findMemberGradeByAlbumIdAndUserId(long albumId, long userId);

    // 어드민인 모든 앨범 찾기
    @Query("SELECT m.album.id FROM Member m WHERE m.user = :user AND m.rules = 'ROLE_ALBUM_ADMIN'")
    List<Long> findAlbumIdByAdminUser(User user);

    // 유저가 속한 앨범 조회
    @Query("SELECT m.album FROM Member m WHERE m.user = :user")
    List<Album> findAlbumByUser(User user);
}
