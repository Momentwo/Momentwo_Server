package cord.eoeo.momentwo.tag.adapter.out.photo.manager;

import cord.eoeo.momentwo.description.application.port.out.manager.DescriptionGetPhotoPort;
import cord.eoeo.momentwo.photo.domain.Photo;
import cord.eoeo.momentwo.tag.advice.exception.NotFoundTagException;
import cord.eoeo.momentwo.tag.application.port.out.album.AlbumTagGenericRepo;
import cord.eoeo.momentwo.tag.application.port.out.photo.PhotoTagGenericRepo;
import cord.eoeo.momentwo.tag.application.port.out.photo.manager.PhotoTagAddPort;
import cord.eoeo.momentwo.tag.domain.AlbumTag;
import cord.eoeo.momentwo.tag.domain.PhotoTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhotoTagAddAdapter implements PhotoTagAddPort {
    private final DescriptionGetPhotoPort descriptionGetPhotoPort;
    private final PhotoTagGenericRepo photoTagGenericRepo;
    private final AlbumTagGenericRepo albumTagGenericRepo;

    @Override
    public void PhotoTagAdd(long albumId, long photoId, long albumTagId) {
        // 설명(작성자) 확인
        Photo photo = descriptionGetPhotoPort.getPhoto(photoId);

        // 태그정보 확인
        AlbumTag albumTag = albumTagGenericRepo.findById(albumTagId).orElseThrow(NotFoundTagException::new);

        PhotoTag photoTag = new PhotoTag(photo, albumTag);
        photoTagGenericRepo.save(photoTag);
    }
}
