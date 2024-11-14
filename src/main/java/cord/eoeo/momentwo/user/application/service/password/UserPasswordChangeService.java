package cord.eoeo.momentwo.user.application.service.password;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cord.eoeo.momentwo.user.adapter.dto.in.ChangePasswordRequestDto;
import cord.eoeo.momentwo.user.advice.exception.PasswordMisMatchException;
import cord.eoeo.momentwo.user.application.port.in.password.UserPasswordChangeUseCase;
import cord.eoeo.momentwo.user.application.port.out.PasswordEncoder;
import cord.eoeo.momentwo.user.application.port.out.UserGenericRepo;
import cord.eoeo.momentwo.user.application.port.out.UserRedisGenericRepo;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidKeyPort;
import cord.eoeo.momentwo.user.application.port.out.valid.UserNicknameValidPort;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserPasswordChangeService implements UserPasswordChangeUseCase {
    private final UserGenericRepo userGenericRepo;
    private final UserNicknameValidPort userNicknameValidPort;
    private final PasswordEncoder passwordEncoder;
    private final UserNicknameValidKeyPort userNicknameValidKeyPort;
    private final UserRedisGenericRepo userRedisGenericRepo;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public void changePassword(ChangePasswordRequestDto changePasswordRequestDto) {
        String newPassword = changePasswordRequestDto.getNewPassword();
        String newPasswordMatch = changePasswordRequestDto.getNewPasswordMatch();

        if(!newPassword.equals(newPasswordMatch)) {
            throw new PasswordMisMatchException();
        }

        User user = userNicknameValidPort.authenticationValid();

        user.setPassword(passwordEncoder.encoder(newPasswordMatch));
        userGenericRepo.save(user);

        // 캐싱된 데이터가 있다면 덮어쓰자
        String key = userNicknameValidKeyPort.getKey(user.getNickname());
        Object isGetRedis = userRedisGenericRepo.get(key);

        if(isGetRedis != null) {
            // 캐시에 데이터 저장
            try {
                userRedisGenericRepo.set(key, objectMapper.writeValueAsString(user));
                userRedisGenericRepo.expire(key, 10L);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
