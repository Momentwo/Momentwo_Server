package cord.eoeo.momentwo.global.application.port.out;

import java.util.Optional;

public interface ElasticsearchGenericDefaultRepo<T, V> {
    void save(T entity);

    Optional<T> findById(V id);

    void deleteById(V id);
}
