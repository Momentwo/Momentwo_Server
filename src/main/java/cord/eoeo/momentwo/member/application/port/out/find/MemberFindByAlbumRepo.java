package cord.eoeo.momentwo.member.application.port.out.find;

import cord.eoeo.momentwo.member.domain.Member;

import java.util.List;

public interface MemberFindByAlbumRepo {
    List<Member> findMemberByAlbum(long albumId);
}
