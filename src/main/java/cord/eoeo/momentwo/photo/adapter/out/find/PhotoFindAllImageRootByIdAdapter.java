package cord.eoeo.momentwo.photo.adapter.out.find;

import cord.eoeo.momentwo.photo.application.port.out.find.PhotoFindAllImageRootByIdPort;
import cord.eoeo.momentwo.photo.application.port.out.jpa.PhotoFindJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PhotoFindAllImageRootByIdAdapter implements PhotoFindAllImageRootByIdPort {
    private final PhotoFindJpaRepo photoFindJpaRepo;

    @Override
    public List<String> photoFindAllImageRootById(List<Long> imagesId) {
        return photoFindJpaRepo.findAllImageRootById(imagesId);
    }
}
