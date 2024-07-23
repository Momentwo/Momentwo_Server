package cord.eoeo.momentwo.album.application.service;

import cord.eoeo.momentwo.album.adapter.dto.AlbumCreateRequestDto;
import cord.eoeo.momentwo.album.adapter.dto.AlbumTitleEditRequestDto;
import cord.eoeo.momentwo.album.advice.exception.NotFoundAlbumException;
import cord.eoeo.momentwo.album.application.port.in.AlbumUseCase;
import cord.eoeo.momentwo.album.application.port.out.AlbumRepository;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.member.application.port.out.AlbumMemberInvite;
import cord.eoeo.momentwo.member.domain.MemberAlbumGrade;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.application.port.out.UserRepository;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlbumService implements AlbumUseCase {
    private final AlbumRepository albumRepository;
    private final AlbumMemberInvite albumMemberInvite;
    private final UserRepository userRepository;
    private final GetAuthentication getAuthentication;

    @Transactional
    @Override
    public void createAlbums(AlbumCreateRequestDto albumCreateRequestDto) {
        User admin = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);
        // 앨범 생성
        Album newAlbum = new Album(albumCreateRequestDto.getCreateTitle());
        albumRepository.save(newAlbum);

        // 앨범 생성자 관리자 권한 부여
        albumMemberInvite.invite(newAlbum, admin, MemberAlbumGrade.ROLE_ALBUM_ADMIN);

        // 앨범에 초대된 멤버 권한 부여
        albumCreateRequestDto.getDoInviteNicknameList().forEach(nickname -> {
            User inviteUser = userRepository.findByNickname(nickname).orElseThrow();
            albumMemberInvite.invite(newAlbum, inviteUser);
        });
    }

    @Transactional
    @Override
    public void editAlbumsTitle(long id, AlbumTitleEditRequestDto albumTitleEditRequestDto) {
        Album album = albumRepository.findById(id).orElseThrow(NotFoundAlbumException::new);
        album.setTitle(albumTitleEditRequestDto.getEditTitle());
        albumRepository.save(album);
    }

    @Transactional
    @Override
    public void deleteAlbums(long id) {
        albumRepository.deleteAlbum(id);
    }
}
