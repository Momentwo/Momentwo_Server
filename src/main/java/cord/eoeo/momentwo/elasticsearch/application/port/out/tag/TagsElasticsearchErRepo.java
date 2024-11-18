package cord.eoeo.momentwo.elasticsearch.application.port.out.tag;

import cord.eoeo.momentwo.elasticsearch.domain.TagsDocument;
import cord.eoeo.momentwo.global.application.port.out.ElasticsearchGenericDefaultJpaRepo;

public interface TagsElasticsearchErRepo extends ElasticsearchGenericDefaultJpaRepo<TagsDocument, String> {
}
