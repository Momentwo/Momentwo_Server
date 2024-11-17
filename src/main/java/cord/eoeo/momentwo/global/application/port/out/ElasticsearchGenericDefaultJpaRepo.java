package cord.eoeo.momentwo.global.application.port.out;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ElasticsearchGenericDefaultJpaRepo<T, V> extends ElasticsearchRepository<T, V> {
}
