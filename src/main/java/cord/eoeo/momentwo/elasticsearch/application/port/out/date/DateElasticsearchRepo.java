package cord.eoeo.momentwo.elasticsearch.application.port.out.date;

import cord.eoeo.momentwo.elasticsearch.domain.DateDocument;
import cord.eoeo.momentwo.global.application.port.out.ElasticsearchGenericDefaultRepo;

public interface DateElasticsearchRepo extends ElasticsearchGenericDefaultRepo<DateDocument, String> {
}
