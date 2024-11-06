package cord.eoeo.momentwo.member.adapter.out.find;

import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.member.application.port.out.find.MemberFindAlbumByUserRepo;
import cord.eoeo.momentwo.member.application.port.out.jpa.AlbumMemberFindJpaRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberFindAlbumByUserAdapter implements MemberFindAlbumByUserRepo {
    private final AlbumMemberFindJpaRepo albumMemberFindJpaRepo;

    @Override
    public List<Album> findAlbumByUser(User user) {
        return albumMemberFindJpaRepo.findAlbumByUser(user);
    }
}
