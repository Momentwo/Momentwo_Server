package cord.eoeo.momentwo.member.application.port.out.info;

import cord.eoeo.momentwo.member.domain.MemberAlbumGrade;
import cord.eoeo.momentwo.user.domain.User;

public interface AssignAdminPort {
    void assignAdmin(long albumId, User changeUser, MemberAlbumGrade rules);
}
