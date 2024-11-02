package cord.eoeo.momentwo.image.application;

import cord.eoeo.momentwo.album.application.port.out.AlbumManager;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.config.s3.S3Manager;
import cord.eoeo.momentwo.image.adapter.dto.PresignedRequestDto;
import cord.eoeo.momentwo.image.adapter.dto.PresignedResponseDto;
import cord.eoeo.momentwo.image.adapter.dto.UserPresignedRequestDto;
import cord.eoeo.momentwo.image.application.port.in.ImageUseCase;
import cord.eoeo.momentwo.image.application.port.out.ImageManager;
import cord.eoeo.momentwo.photo.advice.exception.PhotoCapacityFullException;
import cord.eoeo.momentwo.photo.application.port.out.PhotoRepository;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.application.port.out.find.UserFindNicknameRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService implements ImageUseCase {
    private final ImageManager imageManager;
    private final S3Manager s3Manager;
    private final PhotoRepository photoRepository;
    private final AlbumManager albumManager;
    private final UserFindNicknameRepo userFindNicknameRepo;
    private final GetAuthentication getAuthentication;

    @Override
    @CheckAlbumAccessRules
    public PresignedResponseDto photoPresignedUrl(PresignedRequestDto presignedRequestDto) {
        // 앨범 제한 갯수를 넘지 않는 다면 반환
        Album album = albumManager.getAlbumInfo(presignedRequestDto.getAlbumId());
        if(photoRepository.getAlbumCount(album) >= 1000) {
            throw new PhotoCapacityFullException();
        }
        return new PresignedResponseDto()
                .toDo(imageManager.getPresignedUrl(presignedRequestDto.getExtension(), s3Manager.getImagePath()));
    }

    @Override
    @CheckAlbumAccessRules
    public PresignedResponseDto albumProfilePresignedUrl(PresignedRequestDto presignedRequestDto) {
        Album album = albumManager.getAlbumInfo(presignedRequestDto.getAlbumId());

        // 이미지 삭제(현재) -> 기존 이미지가 없다면 업로드한 이미지만 저장, 기존 이미지가 있다면 삭제 진행
        imageManager.imageDelete(s3Manager.getProfileAlbumPath() + album.getProfileFilename()).join();
        return new PresignedResponseDto()
                .toDo(imageManager.getPresignedUrl(presignedRequestDto.getExtension(), s3Manager.getProfileAlbumPath()));
    }

    @Override
    public PresignedResponseDto userProfilePresignedUrl(UserPresignedRequestDto userPresignedRequestDto) {
        User user = userFindNicknameRepo.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);

        // 이미지 삭제(현재) -> 기존 이미지가 없다면 업로드한 이미지만 저장, 기존 이미지가 있다면 삭제 진행
        imageManager.imageDelete(s3Manager.getProfileUsersPath() + user.getUserProfileImage()).join();
        return new PresignedResponseDto()
                .toDo(imageManager.getPresignedUrl(
                        userPresignedRequestDto.getExtension(), s3Manager.getProfileUsersPath())
                );
    }
}
