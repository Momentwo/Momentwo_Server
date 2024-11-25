package cord.eoeo.momentwo.elasticsearch.adpater.out.date;

import cord.eoeo.momentwo.elasticsearch.application.port.out.date.DateElasticsearchRepo;
import cord.eoeo.momentwo.elasticsearch.application.port.out.date.PhotoDateSaveRepo;
import cord.eoeo.momentwo.elasticsearch.domain.DateDocument;
import cord.eoeo.momentwo.photo.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhotoDateSaveAdapter implements PhotoDateSaveRepo {
    private final DateElasticsearchRepo dateElasticsearchRepo;

    @Override
    public void photoSave(Photo photo) {
        DateDocument dateDocument = new DateDocument(photo);
        dateElasticsearchRepo.save(dateDocument);
    }
}
