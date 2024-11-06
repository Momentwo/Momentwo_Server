package cord.eoeo.momentwo.member.adapter.out.find;

import cord.eoeo.momentwo.member.application.port.out.find.MemberFindByAlbumRepo;
import cord.eoeo.momentwo.member.application.port.out.jpa.AlbumMemberFindJpaRepo;
import cord.eoeo.momentwo.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberFindByAlbumAdapter implements MemberFindByAlbumRepo {
    private final AlbumMemberFindJpaRepo albumMemberFindJpaRepo;

    @Override
    public List<Member> findMemberByAlbum(long albumId) {
        return albumMemberFindJpaRepo.findMemberByAlbum(albumId);
    }
}
