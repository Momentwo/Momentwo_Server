package cord.eoeo.momentwo.global.application.port.out;

public interface RedisDefaultRepo<K, V, T> {
    // 저장
    void set(K key, V data);
    void set(K key, V data, T duration);
    void setHash(K key, V data);

    // 조회
    V get(K key);

    // 갱신
    void update(K key, V value);

    // 삭제
    void delete(K key);

    // 만료 시간
    void expire(K key, V time); // 설정
    long getExpire(K key); // 만료 시간 조회
}
