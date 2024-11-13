package cord.eoeo.momentwo.like.adapter.out;

import cord.eoeo.momentwo.like.application.port.out.PhotoLikesRedisGenericRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class PhotoLikesRedisGenericAdapter implements PhotoLikesRedisGenericRepo {
    private final RedisTemplate<String, Object> redisStringTemplate;

    @Override
    public void set(String key, Object data) {
        redisStringTemplate.opsForValue().set(key, data);
    }

    @Override
    public void set(String key, Object data, Long duration) {
        redisStringTemplate.opsForValue().set(key, data, duration);
    }

    @Override
    public void setHash(String key, Object data) {
        redisStringTemplate.opsForHash().putAll(key, (Map<?, ?>) data);
    }

    @Override
    public Object get(String key) {
        return redisStringTemplate.opsForValue().get(key);
    }

    @Override
    public void update(String key, Object value) {
        redisStringTemplate.opsForValue().set(key, value);
    }

    @Override
    public void delete(String key) {
        redisStringTemplate.delete(key);
    }

    @Override
    public void expire(String key, Object time) {
        redisStringTemplate.expire(key, (Long) time, TimeUnit.MINUTES);
    }

    @Override
    public long getExpire(String key) {
        return redisStringTemplate.getExpire(key);
    }
}
