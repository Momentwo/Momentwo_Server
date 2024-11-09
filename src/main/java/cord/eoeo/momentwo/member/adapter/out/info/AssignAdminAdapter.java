package cord.eoeo.momentwo.member.adapter.out.info;

import cord.eoeo.momentwo.member.application.port.out.AlbumMemberGenericRepo;
import cord.eoeo.momentwo.member.application.port.out.find.MemberFindGradeByAlbumIdAndUserIdRepo;
import cord.eoeo.momentwo.member.application.port.out.info.AssignAdminPort;
import cord.eoeo.momentwo.member.domain.Member;
import cord.eoeo.momentwo.member.domain.MemberAlbumGrade;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AssignAdminAdapter implements AssignAdminPort {
    private final MemberFindGradeByAlbumIdAndUserIdRepo memberFindGradeByAlbumIdAndUserIdRepo;
    private final AlbumMemberGenericRepo albumMemberGenericRepo;

    // 관리자 권한 넘기기
    @Override
    @Transactional
    public void assignAdmin(long albumId, User changeUser, MemberAlbumGrade rules) {
        Member changeMember = memberFindGradeByAlbumIdAndUserIdRepo
                .findMemberGradeByAlbumIdAndUserId(albumId, changeUser.getId());
        changeMember.setRules(rules);
        albumMemberGenericRepo.save(changeMember);
    }
}
