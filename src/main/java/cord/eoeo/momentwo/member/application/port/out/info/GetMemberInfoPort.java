package cord.eoeo.momentwo.member.application.port.out.info;

import cord.eoeo.momentwo.member.domain.Member;

public interface GetMemberInfoPort {
    Member getAlbumMemberInfo(long albumId, long userId);
}
