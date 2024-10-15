package cord.eoeo.momentwo.album.application.service;

import cord.eoeo.momentwo.album.adapter.dto.AlbumProfileRequestDto;
import cord.eoeo.momentwo.album.adapter.dto.AlbumProfileUploadRequestDto;
import cord.eoeo.momentwo.album.adapter.dto.AlbumSubTitleEditRequestDto;
import cord.eoeo.momentwo.album.application.port.in.AlbumProfileUseCase;
import cord.eoeo.momentwo.album.application.port.out.AlbumProfile;
import cord.eoeo.momentwo.member.application.port.out.GetAlbumInfo;
import cord.eoeo.momentwo.member.domain.Member;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.application.port.out.UserRepository;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlbumProfileService implements AlbumProfileUseCase {
    private final AlbumProfile albumProfile;
    private final GetAuthentication getAuthentication;
    private final GetAlbumInfo getAlbumInfo;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void profileUpload(AlbumProfileUploadRequestDto uploadRequestDto) {
        albumProfile.profileUpload(
                getMember(uploadRequestDto.getAlbumId()),
                uploadRequestDto.getFilename()
        );
    }

    @Override
    @Transactional
    public void profileDelete(AlbumProfileRequestDto albumProfileRequestDto) {
        albumProfile.profileDelete(getMember(albumProfileRequestDto.getAlbumId()));
    }

    @Override
    @Transactional
    public void albumSubTitleEdit(AlbumSubTitleEditRequestDto subTitleEditRequestDto) {
        albumProfile.albumSubTitleEdit(
                getMember(subTitleEditRequestDto.getAlbumId()),
                subTitleEditRequestDto.getSubTitle()
        );
    }

    @Override
    @Transactional
    public void albumSubTitleDelete(AlbumProfileRequestDto albumProfileRequestDto) {
        albumProfile.albumSubTitleDelete(getMember(albumProfileRequestDto.getAlbumId()));
    }

    @Transactional(readOnly = true)
    private Member getMember(long albumId) {
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);
        return getAlbumInfo.getAlbumMemberInfo(albumId, user.getId());
    }
}
