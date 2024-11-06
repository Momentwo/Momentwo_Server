package cord.eoeo.momentwo.member.adapter.out.info;

import cord.eoeo.momentwo.member.application.port.out.find.MemberFindGradeByAlbumIdAndUserIdRepo;
import cord.eoeo.momentwo.member.application.port.out.info.GetAlbumMemberInfoPort;
import cord.eoeo.momentwo.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GetAlbumMemberInfoAdapter implements GetAlbumMemberInfoPort {
    private final MemberFindGradeByAlbumIdAndUserIdRepo memberFindGradeByAlbumIdAndUserIdRepo;

    // 앨범 멤버 정보 가져오기
    @Override
    @Transactional(readOnly = true)
    public Member getAlbumMemberInfo(long albumId, long userId) {
        return memberFindGradeByAlbumIdAndUserIdRepo.findMemberGradeByAlbumIdAndUserId(albumId, userId);
    }
}
