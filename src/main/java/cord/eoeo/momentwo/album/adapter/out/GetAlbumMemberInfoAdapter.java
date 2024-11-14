package cord.eoeo.momentwo.album.adapter.out;

import cord.eoeo.momentwo.album.application.port.out.GetAlbumMemberInfoPort;
import cord.eoeo.momentwo.album.application.port.out.manager.GetAlbumInfoPort;
import cord.eoeo.momentwo.member.application.port.out.info.GetMemberInfoPort;
import cord.eoeo.momentwo.member.domain.Member;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GetAlbumMemberInfoAdapter implements GetAlbumMemberInfoPort {
    private final GetAlbumInfoPort getAlbumInfoPort;
    private final UserNicknameValidPort userNicknameValidPort;
    private final GetMemberInfoPort getMemberInfoPort;

    @Override
    @Transactional(readOnly = true)
    public Member getMemberInfo(long id) {
        // 앨범이 존재하는지 확인
        getAlbumInfoPort.getAlbumInfo(id);

        User user = userNicknameValidPort.authenticationValid();
        return getMemberInfoPort.getAlbumMemberInfo(id, user.getId());
    }
}
