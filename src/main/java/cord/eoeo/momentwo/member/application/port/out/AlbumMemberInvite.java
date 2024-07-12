package cord.eoeo.momentwo.member.application.port.out;

import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.user.domain.User;

import java.util.concurrent.CompletableFuture;

public interface AlbumMemberInvite {
    CompletableFuture<Void> invite(Album album, User invitedUser);
}
