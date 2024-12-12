package cord.eoeo.momentwo.photo.application.port.out.find;

import java.util.List;

public interface PhotoFindAllImageRootByIdPort {
    List<String> photoFindAllImageRootById(List<Long> imagesId);
}
