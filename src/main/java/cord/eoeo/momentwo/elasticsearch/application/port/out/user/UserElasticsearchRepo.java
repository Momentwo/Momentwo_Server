package cord.eoeo.momentwo.elasticsearch.application.port.out.user;

import cord.eoeo.momentwo.elasticsearch.domain.UserDocument;
import cord.eoeo.momentwo.global.application.port.out.ElasticsearchGenericDefaultRepo;

public interface UserElasticsearchRepo extends ElasticsearchGenericDefaultRepo<UserDocument, Long> {
}
