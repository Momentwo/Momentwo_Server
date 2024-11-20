package cord.eoeo.momentwo.album.application.service.profile;

import cord.eoeo.momentwo.album.adapter.dto.in.AlbumProfileUploadRequestDto;
import cord.eoeo.momentwo.album.adapter.out.profile.ProfileUploadAdapter;
import cord.eoeo.momentwo.album.application.aop.annotation.CheckAlbumAdmin;
import cord.eoeo.momentwo.album.application.port.in.profile.ProfileUploadUseCase;
import cord.eoeo.momentwo.album.application.port.out.GetAlbumMemberPort;
import cord.eoeo.momentwo.config.s3.S3Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileUploadService implements ProfileUploadUseCase {
    private final ProfileUploadAdapter profileUploadAdapter;
    private final S3Manager s3Manager;
    private final GetAlbumMemberPort getAlbumMemberPort;

    @Override
    @Transactional
    @CheckAlbumAdmin
    public void profileUpload(AlbumProfileUploadRequestDto uploadRequestDto) {
        profileUploadAdapter.profileUpload(
                getAlbumMemberPort.getMember(uploadRequestDto.getAlbumId()),
                s3Manager.getBaseDomain() + uploadRequestDto.getFilename()
        );
    }
}
