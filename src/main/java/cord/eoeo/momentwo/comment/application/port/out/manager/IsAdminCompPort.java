package cord.eoeo.momentwo.comment.application.port.out.manager;

import cord.eoeo.momentwo.user.domain.User;

public interface IsAdminCompPort {
    boolean isAdmin(long albumId, long commentId, User user);
}
