package cord.eoeo.momentwo.user.adapter.out.valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.UserRedisGenericRepo;
import cord.eoeo.momentwo.user.application.port.out.find.UserFindUsernameRepo;
import cord.eoeo.momentwo.user.application.port.out.valid.UsernameValidKeyPort;
import cord.eoeo.momentwo.user.application.port.out.valid.UsernameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsernameValidAdapter implements UsernameValidPort {
    private final UserFindUsernameRepo userFindUsernameRepo;
    private final UsernameValidKeyPort usernameValidKeyPort;
    private final UserRedisGenericRepo userRedisGenericRepo;
    private final ObjectMapper objectMapper;

    @Override
    public User usernameValid(String username) {
        // 캐싱 정보 조회
        String key = usernameValidKeyPort.getKey(username);
        Object isGetRedis = userRedisGenericRepo.get(key);

        if(isGetRedis != null) {
            try {
                return objectMapper.readValue(String.valueOf(isGetRedis), User.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        // DB 데이터 조회
        User user = userFindUsernameRepo.findByUsername(username).orElseThrow(NotFoundUserException::new);

        // 캐시에 데이터 저장
        try {
            userRedisGenericRepo.set(key, objectMapper.writeValueAsString(user));
            userRedisGenericRepo.expire(key, 10L);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return user;
    }
}
