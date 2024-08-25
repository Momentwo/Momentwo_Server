package cord.eoeo.momentwo.photo.application.service;

import cord.eoeo.momentwo.album.application.port.out.AlbumManager;
import cord.eoeo.momentwo.album.domain.Album;
import cord.eoeo.momentwo.image.adapter.dto.ImageViewListResponseDto;
import cord.eoeo.momentwo.image.application.port.out.ImageManager;
import cord.eoeo.momentwo.image.path.ImagePath;
import cord.eoeo.momentwo.member.advice.exception.NotFoundAccessException;
import cord.eoeo.momentwo.photo.adapter.dto.PhotoDeleteRequestDto;
import cord.eoeo.momentwo.photo.adapter.dto.PhotoUploadRequestDto;
import cord.eoeo.momentwo.photo.adapter.dto.PhotoViewListResponseDto;
import cord.eoeo.momentwo.photo.advice.exception.NotDeleteImageException;
import cord.eoeo.momentwo.photo.advice.exception.PhotoUploadFailException;
import cord.eoeo.momentwo.photo.application.port.in.PhotoUseCase;
import cord.eoeo.momentwo.photo.application.port.out.PhotoRepository;
import cord.eoeo.momentwo.photo.application.port.out.PhotoRulesCheck;
import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.photo.domain.PhotoFormat;
import cord.eoeo.momentwo.subAlbum.application.port.out.SubAlbumManager;
import cord.eoeo.momentwo.subAlbum.domain.SubAlbum;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.application.port.out.UserRepository;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoService implements PhotoUseCase {
    private final ImageManager imageManager;
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;
    private final GetAuthentication getAuthentication;
    private final SubAlbumManager subAlbumManager;
    private final PhotoRulesCheck photoRulesCheck;

    @Override
    @Transactional
    public void photoUpload(PhotoUploadRequestDto photoUploadRequestDto) {
        // 유저 정보 및 앨범 정보 가져오기
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);
        SubAlbum subAlbum = subAlbumManager.getSubAlbumInfo(photoUploadRequestDto.getSubAlbumId());

        // 앨범에 해당하는 멤버인지 확인
        if(!photoRulesCheck.isAlbumMember(subAlbum.getAlbum().getId(), user)) {
            throw new NotFoundAccessException();
        }

        try {
            // 이미지 이름 변환 UUID
            String newFilename = imageManager
                    .imageUpload(photoUploadRequestDto.getImages(), ImagePath.SERVER_IMAGE_PATH.getPath())
                    .get();

            // 타입 정보 찾기
            String type = PhotoFormat.findPhotoType(newFilename.split("\\.")[1].toUpperCase()).getType();

            Photo newPhoto = new Photo(newFilename, type, user, subAlbum);

            photoRepository.save(newPhoto);
        } catch (Exception e) {
            throw new PhotoUploadFailException();
        }
    }

    @Override
    @Transactional
    public void photoDelete(PhotoDeleteRequestDto photoDeleteRequestDto) {
        if(photoDeleteRequestDto.getImagesId().isEmpty() || photoDeleteRequestDto.getImagesUrl().isEmpty()) {
            throw new NotDeleteImageException();
        }
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);

        SubAlbum subAlbum = subAlbumManager.getSubAlbumInfo(photoDeleteRequestDto.getSubAlbumId());

        // 앨범에 해당하는 멤버인지 확인
        if(!photoRulesCheck.isAlbumMember(subAlbum.getAlbum().getId(), user)) {
            throw new NotFoundAccessException();
        }

        // 서버에서 삭제
        photoRepository.deleteAllBySubAlbumIdAndPhotoId(
                photoDeleteRequestDto.getSubAlbumId(),
                photoDeleteRequestDto.getImagesId()
        );

        // 이미지 저장소 삭제
        photoDeleteRequestDto.getImagesUrl().forEach(image -> {
            imageManager.imageDelete(ImagePath.SERVER_IMAGE_PATH.getPath(), image);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public PhotoViewListResponseDto photoView(long subAlbumId) {
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);

        SubAlbum subAlbum = subAlbumManager.getSubAlbumInfo(subAlbumId);

        // 앨범에 해당하는 멤버인지 확인
        if(!photoRulesCheck.isAlbumMember(subAlbum.getAlbum().getId(), user)) {
            throw new NotFoundAccessException();
        }

        List<Photo> photoList = photoRepository.findImageBySubAlbumId(subAlbumId);
        List<Photo> imageSaveList = new ArrayList<>();

        photoList.forEach(photo -> {
            Resource resource = imageManager.imageFileSearch(photo.getImageName()).join();
            // 이미지 저장소에 있는 이미지만 조회
            if(resource.exists()) {
                imageSaveList.add(photo);
            }
        });

        return new PhotoViewListResponseDto().toDo(imageSaveList);
    }
}
