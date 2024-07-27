package cord.eoeo.momentwo.member.adapter.out;

import cord.eoeo.momentwo.album.advice.exception.NotFoundAlbumException;
import cord.eoeo.momentwo.album.application.port.out.AlbumRepository;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.member.advice.exception.NotChangeSameAndUpGradeRulesException;
import cord.eoeo.momentwo.member.advice.exception.NotFoundAuthorityException;
import cord.eoeo.momentwo.member.application.port.out.AlbumMemberRepository;
import cord.eoeo.momentwo.member.application.port.out.GetAlbumInfo;
import cord.eoeo.momentwo.member.domain.Member;
import cord.eoeo.momentwo.member.domain.MemberAlbumGrade;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Configuration
@RequiredArgsConstructor
public class GetAlbumInfoImpl implements GetAlbumInfo {
    private final AlbumRepository albumRepository;
    private final AlbumMemberRepository albumMemberRepository;

    @Override
    @Transactional(readOnly = true)
    @Async
    public CompletableFuture<Album> getAlbum(long id) {
        return CompletableFuture.supplyAsync(()
                -> albumRepository.findById(id).orElseThrow(NotFoundAlbumException::new));
    }

    @Override
    @Transactional(readOnly = true)
    public void checkAuthorityAdmin(long albumId, long userId) {
        Member member = albumMemberRepository.findMemberGradeByAlbumIdAndUserId(albumId, userId);
        if(member.getRules().equals(MemberAlbumGrade.ROLE_ALBUM_ADMIN)
                || member.getRules().equals(MemberAlbumGrade.ROLE_ALBUM_SUB_ADMIN)) {
            return;
        }
        throw new NotFoundAuthorityException();
    }

    @Override
    @Transactional
    public void editGrade(long albumId, long userId, String rules) {
        Member editMember = albumMemberRepository.findMemberGradeByAlbumIdAndUserId(albumId, userId);
        editMember.setRules(MemberAlbumGrade.findMemberAlbumGrade(rules));
        albumMemberRepository.save(editMember);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAlbumMemberList(long albumId) {
        return albumMemberRepository.getAllNicknameList(albumId);
    }

    // 멤버 권한 확인 후 변경
    @Override
    @Transactional(readOnly = true)
    public void changeRules(long albumId, User owner, User target, String rules) {
        Member ownerMember = getAlbumMemberInfo(albumId, owner.getId());
        Member targetMember = getAlbumMemberInfo(albumId, target.getId());

        // 우선순위 비교
        if(ownerMember.getRules().getPriority() >= MemberAlbumGrade.findMemberAlbumGrade(rules).getPriority() ||
        ownerMember.getRules().getPriority() == targetMember.getRules().getPriority()) {
            throw new NotChangeSameAndUpGradeRulesException();
        }

        if(ownerMember.getRules().getPriority() < targetMember.getRules().getPriority()) {
            editGrade(albumId, target.getId(), rules);
        }
    }

    // 앨범 멤버 정보 가져오기
    @Override
    @Transactional(readOnly = true)
    public Member getAlbumMemberInfo(long albumId, long userId) {
        return albumMemberRepository.findMemberGradeByAlbumIdAndUserId(albumId, userId);
    }

    // 멤버 추방 (권한 비교 후)
    @Override
    @Transactional
    public void doKickMember(long albumId, User owner, User kickedUser) {
        Member ownerMember = getAlbumMemberInfo(albumId, owner.getId());
        Member kickedMember = getAlbumMemberInfo(albumId, kickedUser.getId());

        if(ownerMember.getRules().getPriority() < kickedMember.getRules().getPriority()) {
            albumMemberRepository.deleteById(kickedMember.getId());
        }
    }

    // 관리자 권한 넘기기
    @Override
    @Transactional
    public void assignAdmin(long albumId, User changeUser, MemberAlbumGrade rules) {
        Member changeMember = albumMemberRepository.findMemberGradeByAlbumIdAndUserId(albumId, changeUser.getId());
        changeMember.setRules(rules);
        albumMemberRepository.save(changeMember);
    }

    // 앨범에 속해 있다면 나가기
    @Override
    @Transactional(readOnly = true)
    public boolean isAlbumOut(long albumId, User selfUser) {
        int albumOut = albumMemberRepository.deleteByAlbumIdAndUserId(albumId, selfUser.getId());
        return albumOut > 0;
    }

    // 유저 중 관리자 권한을 가진 앨범 찾기
    @Override
    @Transactional(readOnly = true)
    public List<Long> getAlbumIdByAdminUser(User user) {
        return albumMemberRepository.findAlbumIdByAdminUser(user);
    }
}
