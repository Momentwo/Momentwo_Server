package cord.eoeo.momentwo.elasticsearch.application.port.out.user;

import cord.eoeo.momentwo.elasticsearch.domain.UserDocument;
import cord.eoeo.momentwo.global.application.port.out.ElasticsearchGenericDefaultJpaRepo;

public interface UserElasticsearchErRepo extends ElasticsearchGenericDefaultJpaRepo<UserDocument, Long> {
}
