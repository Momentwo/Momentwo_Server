package cord.eoeo.momentwo.member.application.service;

import cord.eoeo.momentwo.member.adapter.in.dto.EditGradeListRequestDto;
import cord.eoeo.momentwo.member.advice.exception.NotChangeSelfException;
import cord.eoeo.momentwo.member.application.port.in.EditMembersGradeUseCase;
import cord.eoeo.momentwo.member.application.port.out.async.GetUserInfoByNicknamePort;
import cord.eoeo.momentwo.member.application.port.out.info.ChangeRulesPort;
import cord.eoeo.momentwo.member.application.port.out.info.CheckAuthorityAdminPort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EditMembersGradeService implements EditMembersGradeUseCase {
    private final GetUserInfoByNicknamePort getUserInfoByNicknamePort;
    private final GetAuthentication getAuthentication;
    private final CheckAuthorityAdminPort checkAuthorityAdminPort;
    private final ChangeRulesPort changeRulesPort;

    @Override
    @Transactional
    @CheckAlbumAccessRules
    public void editMembersGrade(EditGradeListRequestDto editGradeListRequestDto) {
        // 권한 변경자의 등급을 확인한다.
        User owner = getUserInfoByNicknamePort.getUserInfoByNickname(getAuthentication.getAuthentication().getName())
                .join();
        checkAuthorityAdminPort.checkAuthorityAdmin(editGradeListRequestDto.getAlbumId(), owner.getId());

        // 호스트가 자신의 권한을 변경하려 할 경우
        if(editGradeListRequestDto.getEditMemberList().containsKey(owner.getNickname())) {
            throw new NotChangeSelfException();
        }

        // 리스트 안에 유저들이 바꿀 수 있는 값인지 확인 후
        // 권한을 변경시킨다.
        editGradeListRequestDto.getEditMemberList().forEach((nickname, rules) -> {
            User target = getUserInfoByNicknamePort.getUserInfoByNickname(nickname).join();
            changeRulesPort.changeRules(editGradeListRequestDto.getAlbumId(), owner, target, rules);
        });
    }
}
