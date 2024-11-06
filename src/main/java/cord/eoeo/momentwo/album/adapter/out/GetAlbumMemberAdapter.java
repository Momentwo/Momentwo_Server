package cord.eoeo.momentwo.album.adapter.out;

import cord.eoeo.momentwo.album.application.port.out.GetAlbumMemberPort;
import cord.eoeo.momentwo.member.application.port.out.info.GetMemberInfoPort;
import cord.eoeo.momentwo.member.domain.Member;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.application.port.out.find.UserFindNicknameRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GetAlbumMemberAdapter implements GetAlbumMemberPort {
    private final UserFindNicknameRepo userFindNicknameRepo;
    private final GetAuthentication getAuthentication;
    private final GetMemberInfoPort getMemberInfoPort;

    @Override
    @Transactional(readOnly = true)
    public Member getMember(long albumId) {
        User user = userFindNicknameRepo.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);
        return getMemberInfoPort.getAlbumMemberInfo(albumId, user.getId());
    }
}
