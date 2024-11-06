package cord.eoeo.momentwo.member.adapter.out.find;

import cord.eoeo.momentwo.member.advice.exception.NotFoundAccessException;
import cord.eoeo.momentwo.member.application.port.out.find.MemberFindGradeByAlbumIdAndUserIdRepo;
import cord.eoeo.momentwo.member.application.port.out.jpa.AlbumMemberFindJpaRepo;
import cord.eoeo.momentwo.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberFindGradeByAlbumIdAndUserIdAdapter implements MemberFindGradeByAlbumIdAndUserIdRepo {
    private final AlbumMemberFindJpaRepo albumMemberFindJpaRepo;

    @Override
    public Member findMemberGradeByAlbumIdAndUserId(long albumId, long userId) {
        return albumMemberFindJpaRepo.findMemberGradeByAlbumIdAndUserId(albumId, userId)
                .orElseThrow(NotFoundAccessException::new);
    }
}
