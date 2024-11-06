package cord.eoeo.momentwo.member.adapter.out.info;

import cord.eoeo.momentwo.member.application.port.out.info.GetAlbumMemberListPort;
import cord.eoeo.momentwo.member.application.port.out.info.IsCheckAlbumOneMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IsCheckAlbumOneMemberAdapter implements IsCheckAlbumOneMemberPort {
    private final GetAlbumMemberListPort getAlbumMemberListPort;

    @Override
    public boolean isCheckAlbumOneMember(long albumId) {
        return getAlbumMemberListPort.getAlbumMemberList(albumId).size() <= 1;
    }
}
