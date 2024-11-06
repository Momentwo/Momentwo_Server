package cord.eoeo.momentwo.member.adapter.out.info;

import cord.eoeo.momentwo.member.application.port.out.info.IsCheckAlbumAdminPort;
import cord.eoeo.momentwo.member.domain.Member;
import cord.eoeo.momentwo.member.domain.MemberAlbumGrade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IsCheckAlbumAdminAdapter implements IsCheckAlbumAdminPort {

    @Override
    public boolean isCheckAlbumAdmin(Member member) {
        return member.getRules().equals(MemberAlbumGrade.ROLE_ALBUM_ADMIN);
    }
}
