package cord.eoeo.momentwo.album.application.port.out.profile;

import cord.eoeo.momentwo.member.domain.Member;

public interface ProfileDeletePort {
    void profileDelete(Member member);
}