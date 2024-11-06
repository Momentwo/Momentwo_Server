package cord.eoeo.momentwo.member.adapter.out.info;

import cord.eoeo.momentwo.member.adapter.out.delete.MemberDeleteAlbumIdAndUserIdAdapter;
import cord.eoeo.momentwo.member.application.port.out.info.IsAlbumOutPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class IsAlbumOutAdapter implements IsAlbumOutPort {
    private final MemberDeleteAlbumIdAndUserIdAdapter memberDeleteAlbumIdAndUserIdAdapter;

    // 앨범에 속해 있다면 나가기
    @Override
    @Transactional(readOnly = true)
    public boolean isAlbumOut(long albumId, User selfUser) {
        int albumOut = memberDeleteAlbumIdAndUserIdAdapter.deleteByAlbumIdAndUserId(albumId, selfUser.getId());
        return albumOut > 0;
    }

}
