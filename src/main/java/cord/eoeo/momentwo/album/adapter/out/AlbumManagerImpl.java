package cord.eoeo.momentwo.album.adapter.out;

import cord.eoeo.momentwo.album.advice.exception.NotFoundAlbumException;
import cord.eoeo.momentwo.album.application.aop.annotation.CheckAlbumAdmin;
import cord.eoeo.momentwo.album.application.port.out.AlbumManager;
import cord.eoeo.momentwo.album.application.port.out.AlbumRepository;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.member.application.port.out.AlbumMemberInvite;
import cord.eoeo.momentwo.member.domain.Member;
import cord.eoeo.momentwo.member.domain.MemberAlbumGrade;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlbumManagerImpl implements AlbumManager {
    private final AlbumMemberInvite albumMemberInvite;
    private final AlbumRepository albumRepository;

    @Override
    @Transactional(readOnly = true)
    public Album getAlbumInfo(long id) {
        return albumRepository.findById(id).orElseThrow(NotFoundAlbumException::new);
    }

    @Override
    @Transactional
    public void albumSave(Album album) {
        albumRepository.save(album);
    }

    @Override
    @Transactional
    public void albumSetAdmin(Album album, User admin) {
        albumMemberInvite.invite(album, admin, MemberAlbumGrade.ROLE_ALBUM_ADMIN);
    }

    @Override
    @Transactional
    public void albumAddMember(Album album, User inviteUser) {
        albumMemberInvite.invite(album, inviteUser);
    }

    @Transactional
    @CheckAlbumAdmin
    @Override
    public void albumEdit(Member member, String editTitle) {
        member.getAlbum().setTitle(editTitle);
        albumRepository.save(member.getAlbum());
    }

    @Transactional
    @CheckAlbumAdmin
    @Override
    public void albumDelete(Member member) {
        albumRepository.deleteAlbum(member.getAlbum().getId());
    }
}
