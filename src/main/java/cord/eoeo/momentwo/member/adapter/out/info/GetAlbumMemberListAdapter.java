package cord.eoeo.momentwo.member.adapter.out.info;

import cord.eoeo.momentwo.member.application.port.out.get.MemberGetAllNicknameRepo;
import cord.eoeo.momentwo.member.application.port.out.info.GetAlbumMemberListPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAlbumMemberListAdapter implements GetAlbumMemberListPort {
    private final MemberGetAllNicknameRepo memberGetAllNicknameRepo;

    @Override
    @Transactional(readOnly = true)
    public List<String> getAlbumMemberList(long albumId) {
        return memberGetAllNicknameRepo.getAllNicknameList(albumId);
    }
}
