package cord.eoeo.momentwo.user.application.service.profile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cord.eoeo.momentwo.config.s3.S3Manager;
import cord.eoeo.momentwo.elasticsearch.application.port.out.UserElasticSearchManager;
import cord.eoeo.momentwo.user.adapter.dto.in.UserProfileUploadRequestDto;
import cord.eoeo.momentwo.user.application.port.in.profile.UserProfileImageUploadUseCase;
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
public class UserProfileImageUploadService implements UserProfileImageUploadUseCase {
    private final UserNicknameValidPort userNicknameValidPort;
    private final S3Manager s3Manager;
    private final UserGenericRepo userGenericRepo;
    private final UserElasticSearchManager userElasticSearchManager;
    private final UserNicknameValidKeyPort userNicknameValidKeyPort;
    private final UserRedisGenericRepo userRedisGenericRepo;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public void usersProfilesImageUpload(UserProfileUploadRequestDto userProfileUploadRequestDto) {
        User user = userNicknameValidPort.authenticationValid();

        user.setUserProfileImage(s3Manager.getBaseDomain() + userProfileUploadRequestDto.getFilename());
        userGenericRepo.save(user);
        userElasticSearchManager.userInfoChange(user);

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
