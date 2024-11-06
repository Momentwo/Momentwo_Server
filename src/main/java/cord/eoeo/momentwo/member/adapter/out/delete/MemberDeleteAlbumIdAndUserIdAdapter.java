package cord.eoeo.momentwo.member.adapter.out.delete;

import cord.eoeo.momentwo.member.application.port.out.delete.MemberDeleteAlbumIdAndUserIdRepo;
import cord.eoeo.momentwo.member.application.port.out.jpa.AlbumMemberDeleteJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberDeleteAlbumIdAndUserIdAdapter implements MemberDeleteAlbumIdAndUserIdRepo {
    private final AlbumMemberDeleteJpaRepo albumMemberDeleteJpaRepo;

    @Override
    public int deleteByAlbumIdAndUserId(long albumId, long userId) {
        return albumMemberDeleteJpaRepo.deleteByAlbumIdAndUserId(albumId, userId);
    }
}
