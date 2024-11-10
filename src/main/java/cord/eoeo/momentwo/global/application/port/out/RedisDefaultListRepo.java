package cord.eoeo.momentwo.global.application.port.out;

import java.util.List;

public interface RedisDefaultListRepo<K, V> {
    // List 자료구조
    // 리스트에 값 추가
    void addToList(K key, V value);

    // 리스트 조회
    List<V> getList(K key);

    // 리스트에서 값 삭제
    void removeFromList(K key, V value);

    // 리스트에서 값 갱신
    void updateInList(K key, int index, V value);
}
