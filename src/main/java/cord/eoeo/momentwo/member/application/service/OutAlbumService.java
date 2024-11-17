package cord.eoeo.momentwo.member.application.service;

import cord.eoeo.momentwo.elasticsearch.application.port.out.like.manager.DeleteByWildNicknamePort;
import cord.eoeo.momentwo.member.adapter.in.dto.MemberOutRequestDto;
import cord.eoeo.momentwo.member.advice.exception.AdminAlbumOutException;
import cord.eoeo.momentwo.member.advice.exception.MemberNotInAlbumException;
import cord.eoeo.momentwo.member.application.port.in.OutAlbumUseCase;
import cord.eoeo.momentwo.member.application.port.out.AlbumMemberGenericRepo;
import cord.eoeo.momentwo.member.application.port.out.async.GetUserInfoByNicknamePort;
import cord.eoeo.momentwo.member.application.port.out.info.GetMemberInfoPort;
import cord.eoeo.momentwo.member.application.port.out.info.IsAlbumOutPort;
import cord.eoeo.momentwo.member.application.port.out.info.IsCheckAlbumAdminPort;
import cord.eoeo.momentwo.member.application.port.out.info.IsCheckAlbumOneMemberPort;
import cord.eoeo.momentwo.member.domain.Member;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OutAlbumService implements OutAlbumUseCase {
    private final GetUserInfoByNicknamePort getUserInfoByNicknamePort;
    private final GetAuthentication getAuthentication;
    private final GetMemberInfoPort getMemberInfoPort;
    private final IsCheckAlbumAdminPort isCheckAlbumAdminPort;
    private final IsCheckAlbumOneMemberPort isCheckAlbumOneMemberPort;
    private final IsAlbumOutPort isAlbumOutPort;
    private final AlbumMemberGenericRepo albumMemberGenericRepo;
    private final DeleteByWildNicknamePort deleteByWildNicknamePort;

    @Override
    @Transactional
    @CheckAlbumAccessRules
    public void outAlbum(MemberOutRequestDto memberOutRequestDto) {
        User selfUser = getUserInfoByNicknamePort.getUserInfoByNickname(getAuthentication.getAuthentication().getName())
                .join();
        Member member = getMemberInfoPort.getAlbumMemberInfo(memberOutRequestDto.getAlbumId(), selfUser.getId());

        // 앨범 관리자가 나갈 경우 멤버가 존재한다면 나갈 수 없음
        // 하지만 멤버가 없을 경우 나갈 수 있음
        if(isCheckAlbumAdminPort.isCheckAlbumAdmin(member)
                && !isCheckAlbumOneMemberPort.isCheckAlbumOneMember(memberOutRequestDto.getAlbumId())) {
            throw new AdminAlbumOutException();
        }

        // 본인이 앨범에 속해있는지 확인한다.
        // 삭제 된 행이 있으면 true, 없으면 false 반환
        if(!isAlbumOutPort.isAlbumOut(memberOutRequestDto.getAlbumId(), selfUser)) {
            throw new MemberNotInAlbumException();
        }

        // 관리자만 있는 앨범에서 관리자가 나갈 경우
        // 앨범도 같이 삭제 되어야 한다.
        if(isCheckAlbumAdminPort.isCheckAlbumAdmin(member)
                && isCheckAlbumOneMemberPort.isCheckAlbumOneMember(memberOutRequestDto.getAlbumId())) {
            albumMemberGenericRepo.deleteById(member.getAlbum().getId());
        }
        deleteByWildNicknamePort.deleteByWildNickname(selfUser.getNickname());
    }
}
