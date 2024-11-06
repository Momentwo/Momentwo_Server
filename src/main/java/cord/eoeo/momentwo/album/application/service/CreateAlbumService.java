package cord.eoeo.momentwo.album.application.service;

import cord.eoeo.momentwo.album.adapter.dto.AlbumCreateRequestDto;
import cord.eoeo.momentwo.album.advice.exception.NotCreateAlbumException;
import cord.eoeo.momentwo.album.application.port.in.CreateAlbumUseCase;
import cord.eoeo.momentwo.album.application.port.out.AlbumGenericRepo;
import cord.eoeo.momentwo.album.application.port.out.manager.AlbumAddMemberPort;
import cord.eoeo.momentwo.album.application.port.out.manager.AlbumSetAdminPort;
import cord.eoeo.momentwo.album.application.port.out.manager.GetAlbumSubTitlePort;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.member.application.port.out.GetAlbumInfo;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.advice.exception.NotInviteUserException;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.application.port.out.find.UserFindNicknameRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateAlbumService implements CreateAlbumUseCase {
    private final UserFindNicknameRepo userFindNicknameRepo;
    private final GetAuthentication getAuthentication;
    private final GetAlbumInfo getAlbumInfo;
    private final GetAlbumSubTitlePort getAlbumSubTitlePort;
    private final AlbumSetAdminPort albumSetAdminPort;
    private final AlbumGenericRepo albumGenericRepo;
    private final AlbumAddMemberPort albumAddMemberPort;

    @Transactional
    @Override
    public void createAlbums(AlbumCreateRequestDto albumCreateRequestDto) {
        User admin = userFindNicknameRepo.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);

        // 계정당 속할 수 있는 앨범 20개
        if(getAlbumInfo.isCheckAlbumSize(admin)) {
            throw new NotCreateAlbumException();
        }

        // 앨범 생성
        Album newAlbum = new Album(
                albumCreateRequestDto.getCreateTitle(),
                getAlbumSubTitlePort.getSubTitle(),
                albumCreateRequestDto.getFilename()
        );
        albumGenericRepo.save(newAlbum);

        // 앨범 생성자 관리자 권한 부여
        albumSetAdminPort.albumSetAdmin(newAlbum, admin);

        // 앨범에 초대된 멤버 권한 부여
        albumCreateRequestDto.getDoInviteNicknameList().forEach(nickname -> {
            User inviteUser = userFindNicknameRepo.findByNickname(nickname)
                    .orElseThrow(NotInviteUserException::new);
            albumAddMemberPort.albumAddMember(newAlbum, inviteUser);
        });
    }
}