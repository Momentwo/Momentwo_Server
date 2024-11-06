package cord.eoeo.momentwo.member.adapter.out.info;

import cord.eoeo.momentwo.member.application.port.out.get.MemberGetAlbumSizeRepo;
import cord.eoeo.momentwo.member.application.port.out.info.IsCheckAlbumSizePort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class IsCheckAlbumSizeAdapter implements IsCheckAlbumSizePort {
    private final MemberGetAlbumSizeRepo memberGetAlbumSizeRepo;

    // 본인이 속한 앨범의 크기에 따라 예외처리
    @Override
    @Transactional
    public boolean isCheckAlbumSize(User user) {
        return memberGetAlbumSizeRepo.getAlbumSize(user) >= 20;
    }
}
