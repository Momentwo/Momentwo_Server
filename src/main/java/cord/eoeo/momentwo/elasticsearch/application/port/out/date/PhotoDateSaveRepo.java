package cord.eoeo.momentwo.elasticsearch.application.port.out.date;

import cord.eoeo.momentwo.photo.domain.Photo;

public interface PhotoDateSaveRepo {
    void photoSave(Photo photo);
}
