package cord.eoeo.momentwo.member.application.service;

import cord.eoeo.momentwo.elasticsearch.application.port.out.like.manager.DeleteByWildNicknamePort;
import cord.eoeo.momentwo.member.advice.exception.NotSelfKickException;
import cord.eoeo.momentwo.member.application.port.in.KickMembersUseCase;
import cord.eoeo.momentwo.member.application.port.out.async.GetUserInfoByIdPort;
import cord.eoeo.momentwo.member.application.port.out.info.CheckAuthorityAdminPort;
import cord.eoeo.momentwo.member.application.port.out.info.DoKickMemberPort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KickMembersService implements KickMembersUseCase {
    private final GetUserInfoByIdPort getUserInfoByIdPort;
    private final CheckAuthorityAdminPort checkAuthorityAdmin;
    private final DoKickMemberPort doKickMember;
    private final DeleteByWildNicknamePort deleteByWildNicknamePort;
    private final UserNicknameValidPort userNicknameValidPort;

    @Override
    @Transactional
    @CheckAlbumAccessRules
    public void kickMembers(Long albumId, List<Long> kickUsersId) {
        User owner = userNicknameValidPort.authenticationValid();
        checkAuthorityAdmin.checkAuthorityAdmin(albumId, owner.getId());

        // 자신 스스로 추방하려 할 경우
        if(kickUsersId.contains(owner.getId())) {
            throw new NotSelfKickException();
        }

        // 앨범 멤버에 포함되는지 확인
        kickUsersId.forEach(id -> {
            User kickedUser = getUserInfoByIdPort.getUserInfoById(id).join();

            // 본인보다 낮은 권한만 추방 가능
            doKickMember.doKickMember(albumId, owner, kickedUser);
            // 검색 엔진 데이터 삭제
            deleteByWildNicknamePort.deleteByWildNickname(kickedUser.getNickname());
        });
    }
}
