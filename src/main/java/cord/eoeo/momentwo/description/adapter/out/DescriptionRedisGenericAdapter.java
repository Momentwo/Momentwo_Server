package cord.eoeo.momentwo.description.adapter.out;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cord.eoeo.momentwo.description.application.port.out.DescriptionRedisGenericRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class DescriptionRedisGenericAdapter implements DescriptionRedisGenericRepo {
    private final RedisTemplate<String, Object> redisJsonTemplate;

    @Override
    public void set(String key, Object data) {
        // LocalDate 주입
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        redisJsonTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));

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
        // LocalDate 주입
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        redisJsonTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));

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
