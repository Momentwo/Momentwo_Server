package cord.eoeo.momentwo.member.application.port.out.find;

import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.user.domain.User;

import java.util.List;

public interface MemberFindAlbumByUserRepo {
    List<Album> findAlbumByUser(User user);
}
