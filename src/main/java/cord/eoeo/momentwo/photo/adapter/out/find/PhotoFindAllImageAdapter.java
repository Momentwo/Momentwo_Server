package cord.eoeo.momentwo.photo.adapter.out.find;

import cord.eoeo.momentwo.photo.application.port.out.find.PhotoFindAllImagePort;
import cord.eoeo.momentwo.photo.application.port.out.jpa.PhotoFindJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PhotoFindAllImageAdapter implements PhotoFindAllImagePort {
    private final PhotoFindJpaRepo photoFindJpaRepo;

    @Override
    public List<String> photoFindAllImage(long albumId) {
        return photoFindJpaRepo.findAllNameByAlbumId(albumId);
    }
}
