package cord.eoeo.momentwo.member.application.service;

import cord.eoeo.momentwo.elasticsearch.application.port.out.LikesElasticSearchManager;
import cord.eoeo.momentwo.member.adapter.in.dto.KickMembersRequestDto;
import cord.eoeo.momentwo.member.advice.exception.NotSelfKickException;
import cord.eoeo.momentwo.member.application.port.in.KickMembersUseCase;
import cord.eoeo.momentwo.member.application.port.out.async.GetUserInfoByNicknamePort;
import cord.eoeo.momentwo.member.application.port.out.info.CheckAuthorityAdminPort;
import cord.eoeo.momentwo.member.application.port.out.info.DoKickMemberPort;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KickMembersService implements KickMembersUseCase {
    private final GetUserInfoByNicknamePort getUserInfoByNicknamePort;
    private final GetAuthentication getAuthentication;
    private final CheckAuthorityAdminPort checkAuthorityAdmin;
    private final DoKickMemberPort doKickMember;
    private final LikesElasticSearchManager likesElasticSearchManager;

    @Override
    @Transactional
    @CheckAlbumAccessRules
    public void kickMembers(KickMembersRequestDto kickMembersRequestDto) {
        User owner = getUserInfoByNicknamePort.getUserInfoByNickname(getAuthentication.getAuthentication().getName())
                .join();
        checkAuthorityAdmin.checkAuthorityAdmin(kickMembersRequestDto.getAlbumId(), owner.getId());

        // 자신 스스로 추방하려 할 경우
        if(kickMembersRequestDto.getKickMemberList().contains(owner.getNickname())) {
            throw new NotSelfKickException();
        }

        // 앨범 멤버에 포함되는지 확인
        kickMembersRequestDto.getKickMemberList().forEach(nickname -> {
            User kickedUser = getUserInfoByNicknamePort.getUserInfoByNickname(nickname).join();

            // 본인보다 낮은 권한만 추방 가능
            doKickMember.doKickMember(kickMembersRequestDto.getAlbumId(), owner, kickedUser);
            likesElasticSearchManager.deleteByWildNickname(kickedUser.getNickname());
        });
    }
}
