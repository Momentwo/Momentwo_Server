package cord.eoeo.momentwo.photo.application.service;

import cord.eoeo.momentwo.album.application.port.out.manager.GetAlbumInfoPort;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.config.s3.S3Manager;
import cord.eoeo.momentwo.photo.adapter.dto.PhotoUploadRequestDto;
import cord.eoeo.momentwo.photo.application.port.in.PhotoUploadUseCase;
import cord.eoeo.momentwo.photo.application.port.out.PhotoGenericRepo;
import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.photo.domain.PhotoFormat;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.subAlbum.application.port.out.manager.GetSubAlbumInfoPort;
import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PhotoUploadService implements PhotoUploadUseCase {
    private final UserNicknameValidPort userNicknameValidPort;
    private final GetAlbumInfoPort getAlbumInfoPort;
    private final GetSubAlbumInfoPort getSubAlbumInfoPort;
    private final S3Manager s3Manager;
    private final PhotoGenericRepo photoGenericRepo;

    @Override
    @Transactional
    @CheckAlbumAccessRules
    public void photoUpload(PhotoUploadRequestDto photoUploadRequestDto) {
        // 유저 정보 및 앨범 정보 가져오기
        User user = userNicknameValidPort.authenticationValid();

        Album album = getAlbumInfoPort.getAlbumInfo(photoUploadRequestDto.getAlbumId());
        SubAlbum subAlbum = getSubAlbumInfoPort.getSubAlbumInfo(photoUploadRequestDto.getSubAlbumId());

        // 타입 정보 찾기
        int fileSplitSize = photoUploadRequestDto.getFilename().split("\\.").length - 1;
        String type = PhotoFormat.findPhotoType(
                photoUploadRequestDto.getFilename().split("\\.")[fileSplitSize].toUpperCase()).getType();

        Photo newPhoto = new Photo(
                s3Manager.getBaseDomain() + photoUploadRequestDto.getFilename(),
                type,
                user,
                album,
                subAlbum
        );

        photoGenericRepo.save(newPhoto);
    }
}
