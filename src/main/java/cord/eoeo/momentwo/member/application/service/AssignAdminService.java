package cord.eoeo.momentwo.member.application.service;

import cord.eoeo.momentwo.member.adapter.in.dto.AssignAdminRequestDto;
import cord.eoeo.momentwo.member.advice.exception.NotChangeAdminException;
import cord.eoeo.momentwo.member.application.port.in.AssignAdminUseCase;
import cord.eoeo.momentwo.member.application.port.out.async.GetUserInfoByNicknamePort;
import cord.eoeo.momentwo.member.application.port.out.info.AssignAdminPort;
import cord.eoeo.momentwo.member.application.port.out.info.GetAlbumMemberInfoPort;
import cord.eoeo.momentwo.member.domain.MemberAlbumGrade;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AssignAdminService implements AssignAdminUseCase {
    private final GetUserInfoByNicknamePort getUserInfoByNicknamePort;
    private final GetAuthentication getAuthentication;
    private final GetAlbumMemberInfoPort getAlbumMemberInfoPort;
    private final AssignAdminPort assignAdminPort;

    @Override
    @Transactional
    @CheckAlbumAccessRules
    public void assignAdmin(AssignAdminRequestDto assignAdminRequestDto) {
        User owner = getUserInfoByNicknamePort.getUserInfoByNickname(getAuthentication.getAuthentication().getName())
                .join();

        // 관리자가 아닐경우
        if(!getAlbumMemberInfoPort.getAlbumMemberInfo(assignAdminRequestDto.getAlbumId(),
                owner.getId()).getRules().equals(MemberAlbumGrade.ROLE_ALBUM_ADMIN)) {
            throw new NotChangeAdminException();
        }
        User changeUser = getUserInfoByNicknamePort.getUserInfoByNickname(assignAdminRequestDto.getNickname()).join();
        // 관리자 -> 일반 멤버
        assignAdminPort
                .assignAdmin(assignAdminRequestDto.getAlbumId(), owner, MemberAlbumGrade.ROLE_ALBUM_COMMON_MEMBER);
        // 일반 멤버 -> 관리자
        assignAdminPort
                .assignAdmin(assignAdminRequestDto.getAlbumId(), changeUser, MemberAlbumGrade.ROLE_ALBUM_ADMIN);
    }
}
