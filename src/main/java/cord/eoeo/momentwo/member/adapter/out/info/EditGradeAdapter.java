package cord.eoeo.momentwo.member.adapter.out.info;

import cord.eoeo.momentwo.member.application.port.out.AlbumMemberGenericRepo;
import cord.eoeo.momentwo.member.application.port.out.find.MemberFindGradeByAlbumIdAndUserIdRepo;
import cord.eoeo.momentwo.member.application.port.out.info.EditGradePort;
import cord.eoeo.momentwo.member.domain.Member;
import cord.eoeo.momentwo.member.domain.MemberAlbumGrade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class EditGradeAdapter implements EditGradePort {
    private final MemberFindGradeByAlbumIdAndUserIdRepo memberFindGradeByAlbumIdAndUserIdRepo;
    private final AlbumMemberGenericRepo albumMemberGenericRepo;

    @Override
    @Transactional
    public void editGrade(long albumId, long userId, String rules) {
        Member editMember = memberFindGradeByAlbumIdAndUserIdRepo.findMemberGradeByAlbumIdAndUserId(albumId, userId);
        editMember.setRules(MemberAlbumGrade.findMemberAlbumGrade(rules));
        albumMemberGenericRepo.save(editMember);
    }
}
