package cord.eoeo.momentwo.global.application.port.out;

public interface RedisDefaultHashRepo<K, F, V> {
    // Hash 자료구조
    // Hash 필드 추가
    void addToHash(K key, F field, V value);

    // Hash 특정 필드 값 조회
    Object getFromHash(K key, F field);

    // Hash 필드 값 갱신
    void updateInHash(K key, F field, V value);

    // Hash 값 제거
    void removeFromHash(K key, F field);
}
