package cord.eoeo.momentwo.user.application.service.profile;

import cord.eoeo.momentwo.config.s3.S3Manager;
import cord.eoeo.momentwo.elasticsearch.application.port.out.UserElasticSearchManager;
import cord.eoeo.momentwo.user.adapter.dto.in.UserProfileUploadRequestDto;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.application.port.in.profile.UserProfileImageUploadUseCase;
import cord.eoeo.momentwo.user.application.port.out.GetAuthentication;
import cord.eoeo.momentwo.user.application.port.out.UserGenericRepo;
import cord.eoeo.momentwo.user.application.port.out.find.UserFindNicknameRepo;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileImageUploadService implements UserProfileImageUploadUseCase {
    private final UserFindNicknameRepo userFindNicknameRepo;
    private final GetAuthentication getAuthentication;
    private final S3Manager s3Manager;
    private final UserGenericRepo userGenericRepo;
    private final UserElasticSearchManager userElasticSearchManager;

    @Override
    @Transactional
    public void usersProfilesImageUpload(UserProfileUploadRequestDto userProfileUploadRequestDto) {
        User user = userFindNicknameRepo.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);

        user.setUserProfileImage(s3Manager.getBaseDomain() + userProfileUploadRequestDto.getFilename());
        userGenericRepo.save(user);
        userElasticSearchManager.userInfoChange(user);
    }
}
