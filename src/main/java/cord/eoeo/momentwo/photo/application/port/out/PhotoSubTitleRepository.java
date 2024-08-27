package cord.eoeo.momentwo.photo.application.port.out;

import cord.eoeo.momentwo.photo.domain.PhotoSubTitle;

import java.util.List;
import java.util.Optional;

public interface PhotoSubTitleRepository {
    void save(PhotoSubTitle photoSubTitle);
    Optional<PhotoSubTitle> findById(long photoSubTitleId);
    List<PhotoSubTitle> findSubTitleBySubAlbumId(long subAlbumId);
}
