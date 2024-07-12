package cord.eoeo.momentwo.member.adapter.out;

import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.member.application.port.out.AlbumMemberInvite;
import cord.eoeo.momentwo.member.application.port.out.AlbumMemberRepository;
import cord.eoeo.momentwo.member.domain.Member;
import cord.eoeo.momentwo.member.domain.MemberAlbumGrade;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AlbumMemberInviteImpl implements AlbumMemberInvite {
    private final AlbumMemberRepository albumMemberRepository;

    // 멤버 초대하기
    @Override
    @Transactional
    @Async
    public CompletableFuture<Void> invite(Album album, User invitedUser) {
        return CompletableFuture.runAsync(() -> {
            Member newMember = new Member(invitedUser, album, MemberAlbumGrade.ROLE_ALBUM_COMMON_MEMBER);
            albumMemberRepository.save(newMember);
        });
    }
}
