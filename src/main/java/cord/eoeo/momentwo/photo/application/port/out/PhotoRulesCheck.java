package cord.eoeo.momentwo.photo.application.port.out;

import cord.eoeo.momentwo.user.domain.User;

public interface PhotoRulesCheck {
    boolean isAlbumMember(long albumId, User user);
}
