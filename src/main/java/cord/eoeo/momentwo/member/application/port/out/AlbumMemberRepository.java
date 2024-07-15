package cord.eoeo.momentwo.member.application.port.out;

import cord.eoeo.momentwo.member.domain.Member;

import java.util.List;

public interface AlbumMemberRepository {
    void save(Member member);

    List<Member> findMemberByAlbum(long albumId);

    Member findMemberGradeByAlbumIdAndUserId(long albumId, long userId);

    List<String> getAllNicknameList(long albumId);

    void deleteById(long id);

    int deleteByAlbumIdAndUserId(long albumId, long userId);
}
