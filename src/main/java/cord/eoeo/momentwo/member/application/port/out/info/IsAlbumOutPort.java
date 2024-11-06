package cord.eoeo.momentwo.member.application.port.out.info;

import cord.eoeo.momentwo.user.domain.User;

public interface IsAlbumOutPort {
    boolean isAlbumOut(long albumId, User selfUser);
}
