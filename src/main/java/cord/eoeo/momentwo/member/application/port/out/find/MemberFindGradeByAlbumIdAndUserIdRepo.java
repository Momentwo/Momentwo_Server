package cord.eoeo.momentwo.member.application.port.out.find;

import cord.eoeo.momentwo.member.domain.Member;

public interface MemberFindGradeByAlbumIdAndUserIdRepo {
    Member findMemberGradeByAlbumIdAndUserId(long albumId, long userId);
}
