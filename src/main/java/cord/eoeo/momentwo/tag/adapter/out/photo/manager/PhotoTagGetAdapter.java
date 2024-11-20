package cord.eoeo.momentwo.tag.adapter.out.photo.manager;

import cord.eoeo.momentwo.tag.application.port.out.photo.jpa.PhotoTagGetJpaRepo;
import cord.eoeo.momentwo.tag.application.port.out.photo.manager.PhotoTagGetPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PhotoTagGetAdapter implements PhotoTagGetPort {
    private final PhotoTagGetJpaRepo photoTagGetJpaRepo;

    @Override
    public List<String> photoTagGet(long photoId) {
        return photoTagGetJpaRepo.photoAllTags(photoId);
    }
}
