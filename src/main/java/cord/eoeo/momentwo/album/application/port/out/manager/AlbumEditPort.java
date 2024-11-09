package cord.eoeo.momentwo.album.application.port.out.manager;

import cord.eoeo.momentwo.member.domain.Member;

public interface AlbumEditPort {
    void albumEdit(Member member, String editTitle);
}
