package cord.eoeo.momentwo.photo.application.service;

import cord.eoeo.momentwo.album.application.port.out.AlbumManager;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.config.s3.S3Manager;
import cord.eoeo.momentwo.image.adapter.dto.ImageViewListResponseDto;
import cord.eoeo.momentwo.image.application.port.out.ImageManager;
import cord.eoeo.momentwo.photo.adapter.dto.PhotoDeleteRequestDto;
import cord.eoeo.momentwo.photo.adapter.dto.PhotoUploadRequestDto;
import cord.eoeo.momentwo.photo.advice.exception.NotDeleteImageException;
import cord.eoeo.momentwo.photo.advice.exception.NotFoundPhotoException;
import cord.eoeo.momentwo.photo.advice.exception.PhotoCapacityFullException;
import cord.eoeo.momentwo.photo.application.port.in.PhotoUseCase;
import cord.eoeo.momentwo.photo.application.port.out.PhotoPageRepository;
import cord.eoeo.momentwo.photo.application.port.out.PhotoRepository;
import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.photo.domain.PhotoFormat;
import cord.eoeo.momentwo.subAlbum.application.aop.annotation.CheckAlbumAccessRules;
import cord.eoeo.momentwo.subAlbum.application.port.out.SubAlbumManager;
import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.application.port.out.UserRepository;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PhotoService implements PhotoUseCase {
    private final ImageManager imageManager;
    private final PhotoRepository photoRepository;
    private final PhotoPageRepository photoPageRepository;
    private final UserRepository userRepository;
    private final GetAuthentication getAuthentication;
    private final SubAlbumManager subAlbumManager;
    private final AlbumManager albumManager;
    private final S3Manager s3Manager;

    @Override
    @Transactional
    @CheckAlbumAccessRules
    public void photoUpload(PhotoUploadRequestDto photoUploadRequestDto) {
        // 유저 정보 및 앨범 정보 가져오기
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);

        Album album = albumManager.getAlbumInfo(photoUploadRequestDto.getAlbumId());
        if(photoRepository.getAlbumCount(album) >= 1000) {
            throw new PhotoCapacityFullException();
        }

        SubAlbum subAlbum = subAlbumManager.getSubAlbumInfo(photoUploadRequestDto.getSubAlbumId());

        // 타입 정보 찾기
        String type = PhotoFormat.findPhotoType(
                photoUploadRequestDto.getFilename().split("\\.")[1].toUpperCase()).getType();

        Photo newPhoto = new Photo(photoUploadRequestDto.getFilename(), type, user, album, subAlbum);

        photoRepository.save(newPhoto);
    }

    @Override
    @Transactional
    @CheckAlbumAccessRules
    public void photoDelete(PhotoDeleteRequestDto photoDeleteRequestDto) {
        if(photoDeleteRequestDto.getImagesId().isEmpty() || photoDeleteRequestDto.getImagesUrl().isEmpty()) {
            throw new NotDeleteImageException();
        }

        // 서버에서 삭제
        photoRepository.deleteAllBySubAlbumIdAndPhotoId(
                photoDeleteRequestDto.getSubAlbumId(),
                photoDeleteRequestDto.getImagesId()
        );

        // 이미지 저장소 삭제
        photoDeleteRequestDto.getImagesUrl().forEach(image -> {
            imageManager.imageDelete(s3Manager.getImagePath() + image).join();
        });
    }

    @Override
    @Transactional(readOnly = true)
    @CheckAlbumAccessRules
    public ImageViewListResponseDto photoView(long albumId, long subAlbumId, long cursor, Pageable pageable) {
        Page<Photo> photoList = photoPageRepository
                .findQPhotoBySubAlbumIdCustomPaging(
                        subAlbumId,
                        pageable,
                        cursor
                );
        if(photoList.isEmpty()) {
            throw new NotFoundPhotoException();
        }

        return new ImageViewListResponseDto().toDo(photoList);
    }
}
