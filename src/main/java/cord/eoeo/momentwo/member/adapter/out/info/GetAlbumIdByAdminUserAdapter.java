package cord.eoeo.momentwo.member.adapter.out.info;

import cord.eoeo.momentwo.member.application.port.out.find.MemberFindAdminUserRepo;
import cord.eoeo.momentwo.member.application.port.out.info.GetAlbumIdByAdminUserPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAlbumIdByAdminUserAdapter implements GetAlbumIdByAdminUserPort {
    private final MemberFindAdminUserRepo memberFindAdminUserRepo;

    // 유저 중 관리자 권한을 가진 앨범 찾기
    @Override
    @Transactional(readOnly = true)
    public List<Long> getAlbumIdByAdminUser(User user) {
        return memberFindAdminUserRepo.findAlbumIdByAdminUser(user);
    }
}
