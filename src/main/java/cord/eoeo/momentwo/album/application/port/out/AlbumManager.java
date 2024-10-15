package cord.eoeo.momentwo.album.application.port.out;

import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.member.domain.Member;
import cord.eoeo.momentwo.user.domain.User;

public interface AlbumManager {
    Album getAlbumInfo(long id);

    void albumSave(Album album);

    void albumSetAdmin(Album album, User admin);

    void albumAddMember(Album album, User inviteUser);

    void albumEdit(Member member, String editTitle);

    void albumDelete(Member member);

    String getBaseImage();

    String getSubTitle();
}
