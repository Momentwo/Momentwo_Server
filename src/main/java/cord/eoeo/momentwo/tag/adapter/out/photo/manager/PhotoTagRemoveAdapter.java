package cord.eoeo.momentwo.tag.adapter.out.photo.manager;

import cord.eoeo.momentwo.description.application.port.out.manager.DescriptionGetPhotoPort;
import cord.eoeo.momentwo.tag.application.port.out.photo.PhotoTagGenericRepo;
import cord.eoeo.momentwo.tag.application.port.out.photo.manager.PhotoTagRemovePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhotoTagRemoveAdapter implements PhotoTagRemovePort {
    private final PhotoTagGenericRepo photoTagGenericRepo;
    private final DescriptionGetPhotoPort descriptionGetPhotoPort;

    @Override
    public void photoTagRemove(long photoId, long photoTagId) {
        // 작성자 확인
        descriptionGetPhotoPort.getPhoto(photoId);

        photoTagGenericRepo.deleteById(photoTagId);
    }
}
