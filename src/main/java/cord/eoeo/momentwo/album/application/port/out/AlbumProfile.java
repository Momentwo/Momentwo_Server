package cord.eoeo.momentwo.album.application.port.out;

import cord.eoeo.momentwo.member.domain.Member;
import org.springframework.web.multipart.MultipartFile;

public interface AlbumProfile {
    void profileUpload(Member member, MultipartFile image);

    void profileDelete(Member member);

    void albumSubTitleEdit(Member member, String subTitle);

    void albumSubTitleDelete(Member member);
}
