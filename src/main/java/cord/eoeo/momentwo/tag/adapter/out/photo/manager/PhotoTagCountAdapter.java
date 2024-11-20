package cord.eoeo.momentwo.tag.adapter.out.photo.manager;

import cord.eoeo.momentwo.tag.application.port.out.photo.jpa.PhotoTagCountRepo;
import cord.eoeo.momentwo.tag.application.port.out.photo.manager.PhotoTagCountPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhotoTagCountAdapter implements PhotoTagCountPort {
    private final PhotoTagCountRepo photoTagCountRepo;

    @Override
    public int photoTagCount(long photoId) {
        return photoTagCountRepo.photoTagCount(photoId);
    }
}
