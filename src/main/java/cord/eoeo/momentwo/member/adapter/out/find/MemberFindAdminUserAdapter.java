package cord.eoeo.momentwo.member.adapter.out.find;

import cord.eoeo.momentwo.member.application.port.out.find.MemberFindAdminUserRepo;
import cord.eoeo.momentwo.member.application.port.out.jpa.AlbumMemberFindJpaRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberFindAdminUserAdapter implements MemberFindAdminUserRepo {
    private final AlbumMemberFindJpaRepo albumMemberFindJpaRepo;

    @Override
    public List<Long> findAlbumIdByAdminUser(User user) {
        return albumMemberFindJpaRepo.findAlbumIdByAdminUser(user);
    }
}
