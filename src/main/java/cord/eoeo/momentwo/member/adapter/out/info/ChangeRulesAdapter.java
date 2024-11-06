package cord.eoeo.momentwo.member.adapter.out.info;

import cord.eoeo.momentwo.member.advice.exception.NotChangeSameAndUpGradeRulesException;
import cord.eoeo.momentwo.member.application.port.out.info.ChangeRulesPort;
import cord.eoeo.momentwo.member.application.port.out.info.EditGradePort;
import cord.eoeo.momentwo.member.application.port.out.info.GetAlbumMemberInfoPort;
import cord.eoeo.momentwo.member.domain.Member;
import cord.eoeo.momentwo.member.domain.MemberAlbumGrade;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ChangeRulesAdapter implements ChangeRulesPort {
    private final GetAlbumMemberInfoPort getAlbumMemberInfoPort;
    private final EditGradePort editGradePort;

    // 멤버 권한 확인 후 변경
    @Override
    @Transactional(readOnly = true)
    public void changeRules(long albumId, User owner, User target, String rules) {
        Member ownerMember = getAlbumMemberInfoPort.getAlbumMemberInfo(albumId, owner.getId());
        Member targetMember = getAlbumMemberInfoPort.getAlbumMemberInfo(albumId, target.getId());

        // 우선순위 비교
        if(ownerMember.getRules().getPriority() >= MemberAlbumGrade.findMemberAlbumGrade(rules).getPriority() ||
                ownerMember.getRules().getPriority() == targetMember.getRules().getPriority()) {
            throw new NotChangeSameAndUpGradeRulesException();
        }

        if(ownerMember.getRules().getPriority() < targetMember.getRules().getPriority()) {
            editGradePort.editGrade(albumId, target.getId(), rules);
        }
    }
}
