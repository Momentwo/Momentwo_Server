package cord.eoeo.momentwo.user.application.service.status;

import cord.eoeo.momentwo.album.application.port.out.AlbumGenericRepo;
import cord.eoeo.momentwo.elasticsearch.application.port.out.like.manager.DeleteByWildNicknamePort;
import cord.eoeo.momentwo.elasticsearch.application.port.out.user.manager.UserDeleteByIdPort;
import cord.eoeo.momentwo.member.advice.exception.AdminAlbumOutException;
import cord.eoeo.momentwo.member.application.port.out.info.GetAlbumIdByAdminUserPort;
import cord.eoeo.momentwo.member.application.port.out.info.GetMemberInfoPort;
import cord.eoeo.momentwo.member.application.port.out.info.IsCheckAlbumAdminPort;
import cord.eoeo.momentwo.member.application.port.out.info.IsCheckAlbumOneMemberPort;
import cord.eoeo.momentwo.member.domain.Member;
import cord.eoeo.momentwo.user.adapter.dto.in.SignOutRequestDto;
import cord.eoeo.momentwo.user.advice.exception.PasswordMisMatchException;
import cord.eoeo.momentwo.user.application.port.in.status.UserSignOutUseCase;
import cord.eoeo.momentwo.user.application.port.out.PasswordEncoder;
import cord.eoeo.momentwo.user.application.port.out.jpa.UserDeleteJpaRepo;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSignOutService implements UserSignOutUseCase {
    private final UserNicknameValidPort userNicknameValidPort;
    private final PasswordEncoder passwordEncoder;
    private final GetAlbumIdByAdminUserPort getAlbumIdByAdminUserPort;
    private final GetMemberInfoPort getMemberInfoPort;
    private final IsCheckAlbumAdminPort isCheckAlbumAdminPort;
    private final IsCheckAlbumOneMemberPort isCheckAlbumOneMemberPort;
    private final AlbumGenericRepo albumGenericRepo;
    private final UserDeleteJpaRepo userDeleteJpaRepo;
    private final UserDeleteByIdPort userDeleteByIdPort;
    private final DeleteByWildNicknamePort deleteByWildNicknamePort;

    // 회원탈퇴
    @Override
    @Transactional
    public void signOut(SignOutRequestDto signOutRequestDto) {
        User user = userNicknameValidPort.authenticationValid();

        // 비밀번호 확인
        if(!passwordEncoder.matches(signOutRequestDto.getPassword(), user.getPassword())) {
            throw new PasswordMisMatchException();
        }

        // 회원 탈퇴 시 앨범 관리자이면서 멤버를 보유한 경우 회원탈퇴 불가
        // 관리자 권한을 넘기면 탈퇴 가능
        getAlbumIdByAdminUserPort.getAlbumIdByAdminUser(user).forEach(albumId -> {
            Member member = getMemberInfoPort.getAlbumMemberInfo(albumId, user.getId());
            // 멤버가 존재하는 앨범에 관리자로 있는 경우 삭제 불가 예외
            if(isCheckAlbumAdminPort.isCheckAlbumAdmin(member)
                    && !isCheckAlbumOneMemberPort.isCheckAlbumOneMember(albumId)) {
                throw new AdminAlbumOutException();
            }
            // 관리자 일 때, 회원탈퇴를 할 경우 앨범 테이블 정보 삭제
            if(isCheckAlbumAdminPort.isCheckAlbumAdmin(member)
                    && isCheckAlbumOneMemberPort.isCheckAlbumOneMember(albumId)) {
                albumGenericRepo.deleteById(member.getAlbum().getId());
            }
        });

        userDeleteJpaRepo.delete(user);
        userDeleteByIdPort.deleteById(user.getId());
        deleteByWildNicknamePort.deleteByWildNickname(user.getNickname());
    }
}
