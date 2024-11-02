package cord.eoeo.momentwo.global.application.port.out;

import java.util.List;
import java.util.Optional;

public interface GenericDefaultRepo<T, V> {
    void save(T entity);
    Optional<T> findById(V id);
    List<T> findAll();
    void deleteById(V id);
}
