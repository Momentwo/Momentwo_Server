package cord.eoeo.momentwo.elasticsearch.adpater.out.tag.photo;

import cord.eoeo.momentwo.elasticsearch.application.port.out.tag.photo.PhotoTagElasticsearchRepo;
import cord.eoeo.momentwo.elasticsearch.application.port.out.tag.photo.PhotoTagsSavePort;
import cord.eoeo.momentwo.elasticsearch.domain.PhotoTagsDocument;
import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PhotoTagsSaveAdapter implements PhotoTagsSavePort {
    private final PhotoTagElasticsearchRepo photoTagElasticsearchRepo;

    @Override
    public void photoTagsSave(Photo photo, List<String> photoTags) {
        PhotoTagsDocument photoTagsDocument = new PhotoTagsDocument(photo, photoTags);
        photoTagElasticsearchRepo.save(photoTagsDocument);
    }
}
