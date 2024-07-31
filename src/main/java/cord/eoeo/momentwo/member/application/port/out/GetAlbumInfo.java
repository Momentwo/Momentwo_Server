package cord.eoeo.momentwo.member.application.port.out;

import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.member.domain.Member;
import cord.eoeo.momentwo.member.domain.MemberAlbumGrade;
import cord.eoeo.momentwo.user.domain.User;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface GetAlbumInfo {
    CompletableFuture<Album> getAlbum(long id);

    // 관리자급 권한 유저인지 확인
    void checkAuthorityAdmin(long albumId, long userId);

    // 앨범 접근 권한 변경
    void editGrade(long albumId, long userId, String rules);

    List<String> getAlbumMemberList(long albumId);

    void changeRules(long albumId, User owner, User target, String rules);

    Member getAlbumMemberInfo(long albumId, long userId);

    void doKickMember(long albumId, User owner, User kickedUser);

    void assignAdmin(long albumId, User changeUser, MemberAlbumGrade rules);

    boolean isAlbumOut(long albumId, User selfUser);

    List<Long> getAlbumIdByAdminUser(User user);

    boolean isCheckAlbumAdmin(Member member);

    boolean isCheckAlbumOneMember(long albumId);
}
