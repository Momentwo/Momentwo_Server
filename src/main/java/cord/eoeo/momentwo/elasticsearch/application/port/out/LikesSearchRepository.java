package cord.eoeo.momentwo.elasticsearch.application.port.out;

import cord.eoeo.momentwo.elasticsearch.domain.LikesDocument;

public interface LikesSearchRepository {
    void save(LikesDocument likesDocument);
}
