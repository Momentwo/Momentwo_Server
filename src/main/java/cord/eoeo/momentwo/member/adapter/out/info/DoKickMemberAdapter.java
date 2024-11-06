package cord.eoeo.momentwo.member.adapter.out.info;

import cord.eoeo.momentwo.member.application.port.out.AlbumMemberGenericRepo;
import cord.eoeo.momentwo.member.application.port.out.info.DoKickMemberPort;
import cord.eoeo.momentwo.member.application.port.out.info.GetAlbumMemberInfoPort;
import cord.eoeo.momentwo.member.domain.Member;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DoKickMemberAdapter implements DoKickMemberPort {
    private final GetAlbumMemberInfoPort getAlbumMemberInfoPort;
    private final AlbumMemberGenericRepo albumMemberGenericRepo;

    // 멤버 추방 (권한 비교 후)
    @Override
    @Transactional
    public void doKickMember(long albumId, User owner, User kickedUser) {
        Member ownerMember = getAlbumMemberInfoPort.getAlbumMemberInfo(albumId, owner.getId());
        Member kickedMember = getAlbumMemberInfoPort.getAlbumMemberInfo(albumId, kickedUser.getId());

        if(ownerMember.getRules().getPriority() < kickedMember.getRules().getPriority()) {
            albumMemberGenericRepo.deleteById(kickedMember.getId());
        }
    }
}
