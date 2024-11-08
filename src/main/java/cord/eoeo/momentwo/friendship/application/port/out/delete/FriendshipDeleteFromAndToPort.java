package cord.eoeo.momentwo.friendship.application.port.out.delete;

import cord.eoeo.momentwo.user.domain.User;

public interface FriendshipDeleteFromAndToPort {
    void deleteByFromUserAndToUser(User fromUser, User toUser);
}
