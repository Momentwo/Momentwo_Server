package cord.eoeo.momentwo.user.adapter.out;

import cord.eoeo.momentwo.user.application.port.out.UserRedisGenericRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class UserRedisGenericAdapter implements UserRedisGenericRepo {
    private final RedisTemplate<String, Object> redisJsonTemplate;

    @Override
    public void set(String key, Object data) {
        redisJsonTemplate.opsForValue().set(key, data);
    }

    @Override
    public void set(String key, Object data, Long duration) {
        redisJsonTemplate.opsForValue().set(key, data, duration);
    }

    @Override
    public void setHash(String key, Object data) {
        redisJsonTemplate.opsForHash().putAll(key, (Map<?, ?>) data);
    }

    @Override
    public Object get(String key) {
        return redisJsonTemplate.opsForValue().get(key);
    }

    @Override
    public void update(String key, Object value) {
        redisJsonTemplate.opsForValue().set(key, value);
    }

    @Override
    public void delete(String key) {
        redisJsonTemplate.delete(key);
    }

    @Override
    public void expire(String key, Object time) {
        redisJsonTemplate.expire(key, (Long) time, TimeUnit.MINUTES);
    }

    @Override
    public long getExpire(String key) {
        return redisJsonTemplate.getExpire(key);
    }
}
