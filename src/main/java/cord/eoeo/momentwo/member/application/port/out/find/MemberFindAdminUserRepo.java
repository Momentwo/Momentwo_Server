package cord.eoeo.momentwo.member.application.port.out.find;

import cord.eoeo.momentwo.user.domain.User;

import java.util.List;

public interface MemberFindAdminUserRepo {
    List<Long> findAlbumIdByAdminUser(User user);
}
