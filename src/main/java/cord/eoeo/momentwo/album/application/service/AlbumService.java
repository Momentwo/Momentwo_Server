package cord.eoeo.momentwo.album.application.service;

import cord.eoeo.momentwo.album.adapter.dto.*;
import cord.eoeo.momentwo.album.advice.exception.NotCreateAlbumException;
import cord.eoeo.momentwo.album.advice.exception.NotDeleteAlbumException;
import cord.eoeo.momentwo.album.advice.exception.NotFoundAlbumException;
import cord.eoeo.momentwo.album.application.port.in.AlbumUseCase;
import cord.eoeo.momentwo.album.application.port.out.AlbumManager;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.config.s3.S3Manager;
import cord.eoeo.momentwo.image.application.port.out.ImageManager;
import cord.eoeo.momentwo.member.application.port.out.AlbumMemberRepository;
import cord.eoeo.momentwo.member.application.port.out.GetAlbumInfo;
import cord.eoeo.momentwo.member.domain.Member;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.advice.exception.NotInviteUserException;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.application.port.out.UserRepository;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService implements AlbumUseCase {
    private final UserRepository userRepository;
    private final GetAuthentication getAuthentication;
    private final GetAlbumInfo getAlbumInfo;
    private final AlbumManager albumManager;
    private final AlbumMemberRepository albumMemberRepository;
    private final ImageManager imageManager;
    private final S3Manager s3Manager;

    @Transactional
    @Override
    public void createAlbums(AlbumCreateRequestDto albumCreateRequestDto) {
        User admin = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);

        // 계정당 속할 수 있는 앨범 20개
        if(getAlbumInfo.isCheckAlbumSize(admin)) {
            throw new NotCreateAlbumException();
        }

        // S3 업로드
        String baseImage = imageManager.imageUpload(
                imageManager.makeMultipartFileByS3Image(
                        s3Manager.getProfileBaseImagePath() + albumManager.getBaseImage()).join(),
                s3Manager.getProfileAlbumPath()
        ).join();

        // 앨범 생성
        Album newAlbum = new Album(
                albumCreateRequestDto.getCreateTitle(),
                albumManager.getSubTitle(),
                baseImage
        );
        albumManager.albumSave(newAlbum);

        // 앨범 생성자 관리자 권한 부여
        albumManager.albumSetAdmin(newAlbum, admin);

        // 앨범에 초대된 멤버 권한 부여
        albumCreateRequestDto.getDoInviteNicknameList().forEach(nickname -> {
            User inviteUser = userRepository.findByNickname(nickname)
                    .orElseThrow(NotInviteUserException::new);
            albumManager.albumAddMember(newAlbum, inviteUser);
        });
    }

    @Transactional
    @Override
    public void editAlbumsTitle(AlbumTitleEditRequestDto albumTitleEditRequestDto) {
        albumManager.albumEdit(getMemberInfo(albumTitleEditRequestDto.getAlbumId()), albumTitleEditRequestDto.getEditTitle());
    }

    @Transactional
    @Override
    public void deleteAlbums(AlbumDeleteRequestDto albumDeleteRequestDto) {
        Member member = getMemberInfo(albumDeleteRequestDto.getAlbumId());
        // 관리자이면서 앨범 속 멤버가 한명 이상인 경우 예외
        if(getAlbumInfo.isCheckAlbumAdmin(member)
                && !getAlbumInfo.isCheckAlbumOneMember(albumDeleteRequestDto.getAlbumId())) {
            throw new NotDeleteAlbumException();
        }
        albumManager.albumDelete(member);
    }

    @Override
    @Transactional(readOnly = true)
    public AlbumInfoListResponseDto getAlbums() {
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);
        List<Album> albums = albumMemberRepository.findAlbumByUser(user);
        if(albums.isEmpty()) {
            throw new NotFoundAlbumException();
        }
        return new AlbumInfoListResponseDto().toDo(albums);
    }

    @Override
    @Transactional(readOnly = true)
    public AlbumRulesResponseDto getAlbumsRules(long albumId) {
        return new AlbumRulesResponseDto().toDo(getMemberInfo(albumId));
    }

    @Transactional(readOnly = true)
    private Member getMemberInfo(long id) {
        // 앨범이 존재하는지 확인
        albumManager.getAlbumInfo(id);

        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);
        return getAlbumInfo.getAlbumMemberInfo(id, user.getId());
    }
}
