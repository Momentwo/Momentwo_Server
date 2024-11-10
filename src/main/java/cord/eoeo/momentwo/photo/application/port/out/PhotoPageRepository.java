package cord.eoeo.momentwo.photo.application.port.out;

import cord.eoeo.momentwo.config.page.CursorPage;
import cord.eoeo.momentwo.photo.domain.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PhotoPageRepository {
    CursorPage<Photo> findQPhotoBySubAlbumIdCustomPaging(long subAlbumId, Pageable pageable, long cursorId);
}
