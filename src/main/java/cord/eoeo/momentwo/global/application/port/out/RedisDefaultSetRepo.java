package cord.eoeo.momentwo.global.application.port.out;

import java.util.Set;

public interface RedisDefaultSetRepo<K, V> {
    // Set 자료구조
    // Set에 값 추가
    void addToSet(K key, V value);

    // Set 조회
    Set<V> getSet(K key);

    // Set 값 삭제
    void removeFromSet(K key, V value);
}
