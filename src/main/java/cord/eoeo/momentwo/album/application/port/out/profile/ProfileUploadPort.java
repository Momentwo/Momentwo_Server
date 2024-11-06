package cord.eoeo.momentwo.album.application.port.out.profile;

import cord.eoeo.momentwo.member.domain.Member;

public interface ProfileUploadPort {
    void profileUpload(Member member, String filename);
}
