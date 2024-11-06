package cord.eoeo.momentwo.member.application.port.out.info;

import cord.eoeo.momentwo.user.domain.User;

import java.util.List;

public interface GetAlbumIdByAdminUserPort {
    List<Long> getAlbumIdByAdminUser(User user);
}
