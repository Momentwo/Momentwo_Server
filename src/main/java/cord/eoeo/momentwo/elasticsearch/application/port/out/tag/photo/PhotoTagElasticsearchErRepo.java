package cord.eoeo.momentwo.elasticsearch.application.port.out.tag.photo;

import cord.eoeo.momentwo.elasticsearch.domain.PhotoTagsDocument;
import cord.eoeo.momentwo.global.application.port.out.ElasticsearchGenericDefaultJpaRepo;

public interface PhotoTagElasticsearchErRepo extends ElasticsearchGenericDefaultJpaRepo<PhotoTagsDocument, String> {
}