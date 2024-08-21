package cord.eoeo.momentwo.photo.adapter.out;

import cord.eoeo.momentwo.member.application.port.out.AlbumMemberRepository;
import cord.eoeo.momentwo.member.domain.Member;
import cord.eoeo.momentwo.photo.application.port.out.PhotoRulesCheck;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhotoRulesCheckImpl implements PhotoRulesCheck {
    private final AlbumMemberRepository albumMemberRepository;

    @Override
    public boolean isAlbumMember(long albumId, User user) {
        Member member = albumMemberRepository.findMemberGradeByAlbumIdAndUserId(albumId, user.getId());
        return member != null;
    }
}
