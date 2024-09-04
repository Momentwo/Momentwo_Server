package cord.eoeo.momentwo.member.application.port.out;

import cord.eoeo.momentwo.member.domain.Member;
import cord.eoeo.momentwo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlbumMemberJpaRepository extends JpaRepository<Member, Long> {
    @Query("SELECT m FROM Member m JOIN m.user u JOIN m.album a WHERE a.id = :albumId")
    List<Member> findMemberByAlbum(long albumId);

    @Query("SELECT m FROM Member m JOIN m.user u JOIN m.album a WHERE a.id = :albumId and u.id = :userId")
    Optional<Member> findMemberGradeByAlbumIdAndUserId(long albumId, long userId);

    @Query("SELECT u.nickname FROM Member m JOIN m.user u JOIN m.album a WHERE a.id = :albumId")
    List<String> getAllNicknameList(long albumId);

    void deleteById(long id);

    @Modifying
    @Query("DELETE FROM Member m WHERE m.album.id = :albumId AND m.user.id = :userId")
    int deleteByAlbumIdAndUserId(long albumId, long userId);

    @Query("SELECT m.album.id FROM Member m WHERE m.user = :user AND m.rules = 'ROLE_ALBUM_ADMIN'")
    List<Long> findAlbumIdByAdminUser(User user);

    @Query("SELECT COUNT(m) FROM Member m WHERE m.user = :user")
    int getAlbumSize(User user);
}
