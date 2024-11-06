package cord.eoeo.momentwo.album.adapter.out;

import cord.eoeo.momentwo.album.application.port.out.GetAlbumMemberInfoPort;
import cord.eoeo.momentwo.album.application.port.out.manager.GetAlbumInfoPort;
import cord.eoeo.momentwo.member.application.port.out.GetAlbumInfo;
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
public class GetAlbumMemberInfoAdapter implements GetAlbumMemberInfoPort {
    private final GetAlbumInfoPort getAlbumInfoPort;
    private final UserFindNicknameRepo userFindNicknameRepo;
    private final GetAuthentication getAuthentication;
    private final GetAlbumInfo getAlbumInfo;

    @Override
    @Transactional(readOnly = true)
    public Member getMemberInfo(long id) {
        // 앨범이 존재하는지 확인
        getAlbumInfoPort.getAlbumInfo(id);

        User user = userFindNicknameRepo.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);
        return getAlbumInfo.getAlbumMemberInfo(id, user.getId());
    }
}
