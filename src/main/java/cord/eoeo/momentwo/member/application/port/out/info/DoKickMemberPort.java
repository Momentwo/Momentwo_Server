package cord.eoeo.momentwo.member.application.port.out.info;

import cord.eoeo.momentwo.user.domain.User;

public interface DoKickMemberPort {
    void doKickMember(long albumId, User owner, User kickedUser);
}
