package cord.eoeo.momentwo.album.adapter.out.manager;

import cord.eoeo.momentwo.album.application.port.out.manager.AlbumSetAdminPort;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.member.application.port.out.AlbumMemberInvite;
import cord.eoeo.momentwo.member.domain.MemberAlbumGrade;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AlbumSetAdminAdapter implements AlbumSetAdminPort {
    private final AlbumMemberInvite albumMemberInvite;

    @Override
    @Transactional
    public void albumSetAdmin(Album album, User admin) {
        albumMemberInvite.invite(album, admin, MemberAlbumGrade.ROLE_ALBUM_ADMIN);
    }
}
