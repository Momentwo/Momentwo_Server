package cord.eoeo.momentwo.album.application.port.out;

import cord.eoeo.momentwo.member.domain.Member;

public interface GetAlbumMemberPort {
    Member getMember(long albumId);
}
