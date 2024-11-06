package cord.eoeo.momentwo.album.adapter.out.manager;

import cord.eoeo.momentwo.album.application.port.out.manager.AlbumAddMemberPort;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.member.application.port.out.AlbumMemberInvite;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AlbumAddMemberAdapter implements AlbumAddMemberPort {
    private final AlbumMemberInvite albumMemberInvite;

    @Override
    @Transactional
    public void albumAddMember(Album album, User inviteUser) {
        albumMemberInvite.invite(album, inviteUser);
    }
}
