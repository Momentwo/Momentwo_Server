package cord.eoeo.momentwo.elasticsearch.application.port.out.tag.photo;

import cord.eoeo.momentwo.photo.domain.Photo;

import java.util.List;

public interface PhotoTagsSavePort {
    void photoTagsSave(Photo photo, List<String> photoTags);
}
