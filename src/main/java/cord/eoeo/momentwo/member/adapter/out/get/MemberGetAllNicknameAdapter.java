package cord.eoeo.momentwo.member.adapter.out.get;

import cord.eoeo.momentwo.member.application.port.out.get.MemberGetAllNicknameRepo;
import cord.eoeo.momentwo.member.application.port.out.jpa.AlbumMemberGetJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberGetAllNicknameAdapter implements MemberGetAllNicknameRepo {
    private final AlbumMemberGetJpaRepo albumMemberGetJpaRepo;

    @Override
    public List<String> getAllNicknameList(long albumId) {
        return albumMemberGetJpaRepo.getAllNicknameList(albumId);
    }
}
