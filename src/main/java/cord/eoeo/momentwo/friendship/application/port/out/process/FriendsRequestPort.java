package cord.eoeo.momentwo.friendship.application.port.out.process;

import cord.eoeo.momentwo.user.domain.User;

public interface FriendsRequestPort {
    void friendsRequest(User from, User to, boolean type);
}
