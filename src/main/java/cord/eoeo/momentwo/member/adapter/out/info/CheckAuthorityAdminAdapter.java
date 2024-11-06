package cord.eoeo.momentwo.member.adapter.out.info;

import cord.eoeo.momentwo.member.advice.exception.NotFoundAuthorityException;
import cord.eoeo.momentwo.member.application.port.out.find.MemberFindGradeByAlbumIdAndUserIdRepo;
import cord.eoeo.momentwo.member.application.port.out.info.CheckAuthorityAdminPort;
import cord.eoeo.momentwo.member.domain.Member;
import cord.eoeo.momentwo.member.domain.MemberAlbumGrade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CheckAuthorityAdminAdapter implements CheckAuthorityAdminPort {
    private final MemberFindGradeByAlbumIdAndUserIdRepo memberFindGradeByAlbumIdAndUserIdRepo;

    @Override
    @Transactional(readOnly = true)
    public void checkAuthorityAdmin(long albumId, long userId) {
        Member member = memberFindGradeByAlbumIdAndUserIdRepo.findMemberGradeByAlbumIdAndUserId(albumId, userId);
        if(member.getRules().equals(MemberAlbumGrade.ROLE_ALBUM_ADMIN)
                || member.getRules().equals(MemberAlbumGrade.ROLE_ALBUM_SUB_ADMIN)) {
            return;
        }
        throw new NotFoundAuthorityException();
    }
}
