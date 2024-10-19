package cord.eoeo.momentwo.album.application.port.out;

import cord.eoeo.momentwo.member.domain.Member;

public interface AlbumProfile {
    void profileUpload(Member member, String filename);

    void profileDelete(Member member);

    void albumSubTitleEdit(Member member, String subTitle);

    void albumSubTitleDelete(Member member);
}
