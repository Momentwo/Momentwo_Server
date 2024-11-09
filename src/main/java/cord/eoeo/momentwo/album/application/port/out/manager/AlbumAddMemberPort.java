package cord.eoeo.momentwo.album.application.port.out.manager;

import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.user.domain.User;

public interface AlbumAddMemberPort {
    void albumAddMember(Album album, User inviteUser);
}
