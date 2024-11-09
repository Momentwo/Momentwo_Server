package cord.eoeo.momentwo.member.application.port.out.jpa;

import cord.eoeo.momentwo.member.application.port.out.AlbumMemberGenericJpaRepo;
import cord.eoeo.momentwo.user.domain.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlbumMemberGetJpaRepo extends AlbumMemberGenericJpaRepo {
    // 앨범 멤버 조회
    @Query("SELECT u.nickname FROM Member m JOIN m.user u JOIN m.album a WHERE a.id = :albumId")
    List<String> getAllNicknameList(long albumId);

    // 앨범 크기
    @Query("SELECT COUNT(m) FROM Member m WHERE m.user = :user")
    int getAlbumSize(User user);
}
