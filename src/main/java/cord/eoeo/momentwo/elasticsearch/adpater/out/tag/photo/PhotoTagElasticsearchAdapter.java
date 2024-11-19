package cord.eoeo.momentwo.elasticsearch.adpater.out.tag.photo;

import cord.eoeo.momentwo.elasticsearch.application.port.out.tag.photo.PhotoTagCountByAlbumIdRepo;
import cord.eoeo.momentwo.elasticsearch.application.port.out.tag.photo.PhotoTagElasticsearchErRepo;
import cord.eoeo.momentwo.elasticsearch.application.port.out.tag.photo.PhotoTagElasticsearchRepo;
import cord.eoeo.momentwo.elasticsearch.domain.PhotoTagsDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PhotoTagElasticsearchAdapter implements PhotoTagElasticsearchRepo {
    private final PhotoTagElasticsearchErRepo photoTagElasticsearchErRepo;
    private final PhotoTagCountByAlbumIdRepo photoTagCountByAlbumIdRepo;

    @Override
    public long countByAlbumId(long albumId) {
        return photoTagCountByAlbumIdRepo.countByAlbumId(albumId);
    }

    @Override
    public void save(PhotoTagsDocument entity) {
        photoTagElasticsearchErRepo.save(entity);
    }

    @Override
    public Optional<PhotoTagsDocument> findById(String id) {
        return photoTagElasticsearchErRepo.findById(id);
    }

    @Override
    public void deleteById(String id) {
        photoTagElasticsearchErRepo.deleteById(id);
    }
}
