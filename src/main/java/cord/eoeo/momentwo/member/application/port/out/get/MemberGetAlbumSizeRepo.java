package cord.eoeo.momentwo.member.application.port.out.get;

import cord.eoeo.momentwo.user.domain.User;

public interface MemberGetAlbumSizeRepo {
    int getAlbumSize(User user);
}
