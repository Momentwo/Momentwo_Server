package cord.eoeo.momentwo.user.application.service.profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import cord.eoeo.momentwo.user.adapter.dto.out.UserProfileResponseDto;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.in.profile.UserProfileKeyPort;
import cord.eoeo.momentwo.user.application.port.in.profile.UserProfileUseCase;
import cord.eoeo.momentwo.user.application.port.out.UserRedisGenericRepo;
import cord.eoeo.momentwo.user.application.port.out.find.UserFindNicknameRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileService implements UserProfileUseCase {
    private final UserFindNicknameRepo userFindNicknameRepo;
    private final UserRedisGenericRepo userRedisGenericRepo;
    private final UserProfileKeyPort userProfileKeyPort;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public UserProfileResponseDto getUserProfile(String nickname) {
        // 캐시 정보 확인
        String key = userProfileKeyPort.getKey(nickname);
        Object isGetRedis = userRedisGenericRepo.get(key);
        if(isGetRedis != null) {
            return objectMapper.convertValue(isGetRedis, UserProfileResponseDto.class);
        }

        // DB 확인
        User user = userFindNicknameRepo.findByNickname(nickname).orElseThrow(NotFoundUserException::new);
        UserProfileResponseDto profileResponseDto = new UserProfileResponseDto().toDo(user);

        // 캐시에 데이터 저장
        userRedisGenericRepo.set(key, profileResponseDto);
        userRedisGenericRepo.expire(key, 10L);

        return profileResponseDto;
    }
}
