package cord.eoeo.momentwo.member.adapter.out.get;

import cord.eoeo.momentwo.member.application.port.out.get.MemberGetAlbumSizeRepo;
import cord.eoeo.momentwo.member.application.port.out.jpa.AlbumMemberGetJpaRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberGetAlbumSizeAdapter implements MemberGetAlbumSizeRepo {
    private final AlbumMemberGetJpaRepo albumMemberGetJpaRepo;

    @Override
    public int getAlbumSize(User user) {
        return albumMemberGetJpaRepo.getAlbumSize(user);
    }
}
