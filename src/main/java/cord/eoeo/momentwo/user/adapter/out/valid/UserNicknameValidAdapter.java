package cord.eoeo.momentwo.user.adapter.out.valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.application.port.out.UserRedisGenericRepo;
import cord.eoeo.momentwo.user.application.port.out.find.UserFindNicknameRepo;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidKeyPort;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserNicknameValidAdapter implements UserNicknameValidPort {
    private final UserFindNicknameRepo userFindNicknameRepo;
    private final GetAuthentication getAuthentication;
    private final UserNicknameValidKeyPort userNicknameValidKeyPort;
    private final UserRedisGenericRepo userRedisGenericRepo;
    private final ObjectMapper objectMapper;

    @Override
    public User authenticationValid() {
        // 캐싱 정보 조회
        String nickname = getAuthentication.getAuthentication().getName();
        String key = userNicknameValidKeyPort.getKey(nickname);
        Object isGetRedis = userRedisGenericRepo.get(key);

        if(isGetRedis != null) {
            try {
                return objectMapper.readValue(String.valueOf(isGetRedis), User.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        // DB 데이터 조회
        User user = userFindNicknameRepo.findByNickname(nickname).orElseThrow(NotFoundUserException::new);

        // 캐시에 데이터 저장
        try {
            userRedisGenericRepo.set(key, objectMapper.writeValueAsString(user));
            userRedisGenericRepo.expire(key, 10L);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    @Override
    public User userNicknameValid(String nickname) {
        // 캐싱 정보 조회
        String key = userNicknameValidKeyPort.getKey(nickname);
        Object isGetRedis = userRedisGenericRepo.get(key);

        if(isGetRedis != null) {
            try {
                return objectMapper.readValue(String.valueOf(isGetRedis), User.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        // DB 데이터 조회
        User user = userFindNicknameRepo.findByNickname(nickname).orElseThrow(NotFoundUserException::new);

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
